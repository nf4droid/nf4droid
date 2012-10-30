package at.tugraz.iaik.nf4droid.client.view;

import at.tugraz.iaik.nf4droid.client.activity.mapper.BreadcrumbActivityMapper;
import at.tugraz.iaik.nf4droid.client.activity.mapper.MainContentActivityMapper;
import at.tugraz.iaik.nf4droid.client.general.AppEntryPointWidget;
import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.place.AddAppPlace;
import at.tugraz.iaik.nf4droid.client.place.AppListPlace;
import at.tugraz.iaik.nf4droid.client.view.common.bootstrap.NavWidget;

import com.github.gwtbootstrap.client.ui.Nav;
import com.github.gwtbootstrap.client.ui.Navbar;
import com.github.gwtbootstrap.client.ui.constants.IconSize;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

public class AnalyticsAppMain extends Composite implements AppEntryPointWidget,
		IsWidget {

	interface AnalyticsAppMainUiBinder extends
			UiBinder<Widget, AnalyticsAppMain> {
	}

	private static final AnalyticsAppMainUiBinder uiBinder = GWT
			.create(AnalyticsAppMainUiBinder.class);

	@UiField
	protected Navbar navBar;
	@UiField
	protected ScrollPanel mainPanel;
	@UiField
	protected SimplePanel mainContent;
	@UiField
	protected SimplePanel breadcrumb;
	@UiField(provided = true)
	protected Nav navWidget;

	public AnalyticsAppMain(ClientFactory clientFactory) {
		final NavWidget nav = new NavWidget();
		nav.addLink("Apps", "List of all apps", clientFactory
				.getHistoryMapper().getToken(new AppListPlace()), true,
				IconType.LIST_ALT, IconSize.DEFAULT);
		nav.addLink("Add Capture", "Add app traffic capture", clientFactory
				.getHistoryMapper().getToken(new AddAppPlace()), false,
				IconType.PLUS, IconSize.DEFAULT);
		navWidget = nav;

		initWidget(uiBinder.createAndBindUi(this));

		EventBus eventBus = clientFactory.getEventBus();

		ActivityMapper centerActivityMapper = new MainContentActivityMapper(
				clientFactory);
		ActivityManager centerActivityManager = new ActivityManager(
				centerActivityMapper, eventBus);
		centerActivityManager.setDisplay(mainContent);

		BreadcrumbActivityMapper breadcrumbActivityMapper = new BreadcrumbActivityMapper(
				clientFactory);
		ActivityManager breadcrumbActivityManager = new ActivityManager(
				breadcrumbActivityMapper, eventBus);
		breadcrumbActivityManager.setDisplay(breadcrumb);

	}

	@Override
	public Widget getAppWidget() {
		return mainPanel;
	}

}
