package at.tugraz.iaik.nf4droid.client.activity;

import java.util.Date;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;
import at.tugraz.iaik.nf4droid.client.view.common.list.ListAsyncDataProvider;
import at.tugraz.iaik.nf4droid.client.view.common.list.view.ListView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public abstract class AbstractListActivity<T extends EntityProxy> extends
		BaseActivity {

	private static final long SEARCH_DELAY = 800L;

	protected ListView<T> view;
	protected ListAsyncDataProvider<T> dataProvider;
	private Date lastSearchTime = new Date();
	private Timer searchDelayTimer;

	public AbstractListActivity(final ListView<T> view, final ClientFactory clientFactory) {
		super(clientFactory);
		this.view = view;
		initDataProvider();
	}

	public abstract void initDataProvider();

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		dataProvider.setDataDisplay(view.getTable(), view);
		super.start(panel, eventBus);
	}

	@Override
	public void onStop() {
		dataProvider.removeDataDisplay(view.getTable(), view);
		super.onStop();
	}
	
	@Override
	public void bind() {

		// select table entry
		registerHandler(view
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						@SuppressWarnings("unchecked")
						T selection = (T) ((NoSelectionModel<T>) event
								.getSource()).getLastSelectedObject();
						GWT.log("List entry selected.");
						onListEntrySelected(selection);
					}
				}));

		// column sort
		registerHandler(view
				.addColumnSortEventHandler(new ColumnSortEvent.Handler() {

					@Override
					public void onColumnSort(ColumnSortEvent event) {
						view.goToFirstPage();
					}

				}));

		registerHandler(view
				.addColumnSortEventHandler(new ColumnSortEvent.AsyncHandler(
						view.getTable())));

		// submit search
		registerHandler(view.addSubmitSearchClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				GWT.log("Apply table search (click event).");
				updateView();
				clearSearchDelayTimer();
			}
		}));

		// clear search
		registerHandler(view.addClearSearchClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				GWT.log("Clear table search (click event).");
				view.clearSearch();
				updateView();
				clearSearchDelayTimer();
			}
		}));

		// search key stroke
		registerHandler(view.addTypeSearchKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				final int keyCode = event.getNativeKeyCode();
				if (keyCode == KeyCodes.KEY_ENTER) {
					GWT.log("Apply table search (key event - enter).");
					updateView();
					clearSearchDelayTimer();
				} else if (keyCode == KeyCodes.KEY_ESCAPE) {
					GWT.log("Clear table search (key event - escape).");
					view.clearSearch();
					updateView();
					clearSearchDelayTimer();
				} else {
					final long durationFromLastSearch = System
							.currentTimeMillis() - lastSearchTime.getTime();
					if (durationFromLastSearch < SEARCH_DELAY) {
						if (searchDelayTimer == null) {
							searchDelayTimer = new Timer() {

								@Override
								public void run() {
									GWT.log("Apply table search (key event - instant search - after wait time).");
									updateView();
									clearSearchDelayTimer();
								}
							};
							searchDelayTimer.schedule(CommonUiUtil
									.safeLongToInt(SEARCH_DELAY
											- durationFromLastSearch));
						}
					} else {
						GWT.log("Apply table search (key event - instant search).");
						updateView();
					}
					lastSearchTime = new Date();
				}
			}
		}));
	}

	protected abstract void onListEntrySelected(T proxy);

	private void clearSearchDelayTimer() {
		if (searchDelayTimer != null) {
			searchDelayTimer.cancel();
			searchDelayTimer = null;
		}
	}

	protected void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	protected void updateView() {
		view.goToFirstPage();
		dataProvider.onRangeChanged(view.getTable());
	}

}
