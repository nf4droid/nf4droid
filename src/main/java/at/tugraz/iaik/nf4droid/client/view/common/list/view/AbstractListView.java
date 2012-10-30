package at.tugraz.iaik.nf4droid.client.view.common.list.view;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;
import at.tugraz.iaik.nf4droid.client.view.common.bootstrap.Span;
import at.tugraz.iaik.nf4droid.client.view.common.event.GetValue;
import at.tugraz.iaik.nf4droid.client.view.common.list.CellTableProgressBar;
import at.tugraz.iaik.nf4droid.client.view.common.list.FixedSimplePager;
import at.tugraz.iaik.nf4droid.client.view.common.list.SortableColumn;

import com.github.gwtbootstrap.client.ui.AlertBlock;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.NavWidget;
import com.github.gwtbootstrap.client.ui.Pagination;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.github.gwtbootstrap.client.ui.constants.IconSize;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.RowCountChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public abstract class AbstractListView<T extends EntityProxy> extends Composite
		implements ListView<T> {

	private static final int PAGE_SIZE = 10;
	private static final int FAST_FORWARD_ROWS = 10;
	private static final boolean FAST_FORWARD_BUTTON = true;
	private static final boolean LAST_PAGE_BUTTON = true;
	private static final String EMPTY_TABLE_MSG = "No entries available.";
	protected static final String ROW_COUNT_INFO_PREFIX = "Total: ";

	private static AbstractListViewUiBinder uiBinder = GWT
			.create(AbstractListViewUiBinder.class);

	protected interface AbstractListViewUiBinder extends
			UiBinder<Widget, AbstractListView<?>> {
	}

	@UiField
	protected HTMLPanel htmlPanel;
	
	@UiField(provided = true)
	protected CellTable<T> table;

	@UiField
	protected TextBox searchTextBox;

	@UiField
	protected Button submitSearchButton;

	@UiField
	protected Button clearSearchButton;

	@UiField
	protected Pagination pagination;
	
	@UiField
	protected Span rowCountInfo;
	
	@UiField
	protected com.github.gwtbootstrap.client.ui.Column optionsArea;
	
	@UiField
	protected AlertBlock alertBlock;

	private SimplePager pager;
	private NoSelectionModel<T> selectionModel;

	public AbstractListView() {
		initTable();
		initPager();

		initWidget(createAndBindUi());

		rebuildPager();
	}

	protected static AbstractListViewUiBinder getUiBinder() {
		return uiBinder;
	}

	protected abstract Widget createAndBindUi();

	private void initTable() {
		table = new CellTable<T>(PAGE_SIZE);
		table.setWidth("100%", true);
		table.setEmptyTableWidget(new Span(EMPTY_TABLE_MSG));
		table.setLoadingIndicator(new CellTableProgressBar());

		selectionModel = new NoSelectionModel<T>();
		table.setSelectionModel(selectionModel);

		initTableColumns();
	}

	protected abstract void initTableColumns();

	/**
	 * Add a sortable column to the table.
	 * 
	 * @param <C>
	 *            the data type for the column
	 * @param text
	 *            the header text
	 * @param cell
	 *            the cell used to render the column
	 * @param getter
	 *            the getter to retrieve the value for the column
	 * @param property
	 *            the property to sort by
	 * @param width
	 * 			  the width in percent 
	 * @return the column
	 */
	protected <C> Column<T, C> addColumn(final String text, final Cell<C> cell,
			final GetValue<T, C> getter, final String property, final double width) {

		final SortableColumn<T, C> column = new SortableColumn<T, C>(cell,
				getter, property);

		final TextHeader header = new TextHeader(text);

		column.setSortable(true);
		table.addColumn(column, header);
		table.setColumnWidth(column, width, Unit.PCT);
		return column;
	}

	private void initPager() {
		pager = new FixedSimplePager(
				SimplePager.TextLocation.RIGHT,
				GWT.<SimplePager.Resources> create(SimplePager.Resources.class),
				FAST_FORWARD_BUTTON, FAST_FORWARD_ROWS, LAST_PAGE_BUTTON);
		pager.setPageSize(PAGE_SIZE);
		table.addRangeChangeHandler(new RangeChangeEvent.Handler() {

			@Override
			public void onRangeChange(RangeChangeEvent event) {
				rebuildPager();
			}
		});
		table.addRowCountChangeHandler(new RowCountChangeEvent.Handler() {

			@Override
			public void onRowCountChange(RowCountChangeEvent event) {
				rebuildPager();
				rowCountInfo.setText(ROW_COUNT_INFO_PREFIX+event.getNewRowCount());
			}
		});
		pager.setDisplay(table);
	}

	private void rebuildPager() {
		pagination.clear();

		if (pager.getPageCount() == 0) {
			return;
		}

		NavWidget prev = new NavWidget();
		prev.setIcon(IconType.CARET_LEFT);
		prev.setIconSize(IconSize.LARGE);
		prev.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				pager.previousPage();
			}
		});
		prev.setDisabled(!pager.hasPreviousPage());
		pagination.add(prev);

		int before = 2;
		int after = 2;

		while (!pager.hasPreviousPages(before) && before > 0) {
			before--;
			if (pager.hasNextPages(after + 1)) {
				after++;
			}
		}

		while (!pager.hasNextPages(after) && after > 0) {
			after--;
			if (pager.hasPreviousPages(before + 1)) {
				before++;
			}
		}

		for (int i = pager.getPage() - before; i <= pager.getPage() + after; i++) {

			final int index = i + 1;

			NavWidget page = new NavWidget();
			page.setText(String.valueOf(index));
			page.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					pager.setPage(index - 1);
				}
			});

			if (i == pager.getPage()) {
				page.setActive(true);
			}
			pagination.add(page);
		}

		NavWidget next = new NavWidget();
		next.setIcon(IconType.CARET_RIGHT);
		next.setIconSize(IconSize.LARGE);
		next.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				pager.nextPage();
			}
		});
		next.setDisabled(!pager.hasNextPage());
		pagination.add(next);
	}

	@Override
	public HasData<T> getTable() {
		return table;
	}

	@Override
	public String getSearchString() {
		return searchTextBox.getValue();
	}

	public ColumnSortList getColumnSortList() {
		return table.getColumnSortList();
	}

	public void goToFirstPage() {
		pager.firstPage();
	}

	public void clearSearch() {
		searchTextBox.setValue(null);
	}

	@Override
	public HandlerRegistration addSelectionChangeHandler(
			SelectionChangeEvent.Handler handler) {
		return selectionModel.addSelectionChangeHandler(handler);
	}

	public HandlerRegistration addSubmitSearchClickHandler(ClickHandler handler) {
		return submitSearchButton.addClickHandler(handler);
	}

	public HandlerRegistration addClearSearchClickHandler(ClickHandler handler) {
		return clearSearchButton.addClickHandler(handler);
	}

	public HandlerRegistration addTypeSearchKeyUpHandler(KeyUpHandler handler) {
		return searchTextBox.addKeyUpHandler(handler);
	}

	public HandlerRegistration addColumnSortEventHandler(
			ColumnSortEvent.Handler handler) {
		return table.addColumnSortHandler(handler);
	}
	
	public void showMessage(AlertType alertType, final String heading,
			final String message) {
		alertBlock.setType(alertType);
		alertBlock.setClose(true);
		alertBlock.setText(message);
		alertBlock.setHeading(heading);
		alertBlock.setVisible(true);

		final Timer t = new Timer() {
			public void run() {
				alertBlock.clear();
				alertBlock.setVisible(false);
			}
		};

		t.schedule(CommonUiUtil.ERROR_MESSAGE_HIDE_DELAY);
	}

	public void hideMessage() {
		alertBlock.clear();
		alertBlock.setVisible(false);
	}

}
