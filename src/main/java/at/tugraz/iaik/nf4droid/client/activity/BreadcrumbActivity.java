package at.tugraz.iaik.nf4droid.client.activity;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.place.AddAppPlace;
import at.tugraz.iaik.nf4droid.client.place.AppListPlace;
import at.tugraz.iaik.nf4droid.client.place.AppVersionListPlace;
import at.tugraz.iaik.nf4droid.client.place.CaptureDashboardPlace;
import at.tugraz.iaik.nf4droid.client.place.HttpRequestListPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficCaptureListPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficGeoChartPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficTimeLinePlace;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.AppProxy;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.AppVersionProxy;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.TrafficCaptureProxy;
import at.tugraz.iaik.nf4droid.client.view.breadcrumb.BreadcrumbView;
import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class BreadcrumbActivity extends BaseActivity {

	private BreadcrumbView view;
	private Place place;

	public BreadcrumbActivity(final BreadcrumbView view,
			final ClientFactory clientFactory, final Place place) {
		super(clientFactory);
		this.view = view;
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());

		GWT.log("Start of activity for place: " + place.toString());

		buildBreadcrumb();

		super.start(panel, eventBus);
	}

	private void buildBreadcrumb() {
		view.clearBreadcrumb();

		if ((place instanceof AppListPlace)) {
			view.addTextEntry("Apps");
			view.setHeader("Apps", "List of all apps.");
		} else if (place instanceof AppVersionListPlace) {
			clientFactory.getRequestFactory().appRequest()
					.findApp(((AppVersionListPlace) place).getAppId())
					.fire(new Receiver<AppProxy>() {

						@Override
						public void onSuccess(AppProxy response) {
							addAppsBreadCrumbEntry();
							addAppBreadcumbEntry(response.getId(),
									response.getAppName());
							view.addTextEntry("Versions");
							view.setHeader("App versions", "List of all app versions.");
						}
					});

		} else if (place instanceof TrafficCaptureListPlace) {
			GWT.log("Request for cap place: "
					+ ((TrafficCaptureListPlace) place).getAppVersionId());
			clientFactory
					.getRequestFactory()
					.appVersionRequest()
					.findAppVersion(
							((TrafficCaptureListPlace) place).getAppVersionId())
					.with("app.id", "app.appName")
					.fire(new Receiver<AppVersionProxy>() {

						@Override
						public void onSuccess(AppVersionProxy response) {
							addAppsBreadCrumbEntry();
							addAppBreadcumbEntry(response.getApp().getId(),
									response.getApp().getAppName());
							addAppVersionBreadcrumbEntry(response.getId(),
									response.getVersionCode().toString());
							view.addTextEntry("Traffic Captures");
							view.setHeader("Traffic captures", "List of all traffic captures.");
						}
					});
		} else if (place instanceof CaptureDashboardPlace) {
			GWT.log("Request for dash place: "
					+ ((CaptureDashboardPlace) place).getTrafficCaptureId());
			clientFactory
					.getRequestFactory()
					.trafficCaptureRequest()
					.findTrafficCapture(
							((CaptureDashboardPlace) place)
									.getTrafficCaptureId())
					.with("appVersion.id", "appVersion.versionCode",
							"appVersion.app.appName", "appVersion.app.id")
					.fire(new Receiver<TrafficCaptureProxy>() {

						@Override
						public void onSuccess(TrafficCaptureProxy response) {
							addAppsBreadCrumbEntry();
							addAppBreadcumbEntry(response.getAppVersion()
									.getApp().getId(), response.getAppVersion()
									.getApp().getAppName());
							addAppVersionBreadcrumbEntry(response
									.getAppVersion().getId(), response
									.getAppVersion().getVersionCode()
									.toString());
							addTrafficCaptureBreadcrumbEntry(response.getId(),
									CommonUiUtil.DEFAULT_DATE_TIME_FORMAT
											.format(response.getDateAdded()));
							view.addTextEntry("Dashboard");
							view.setHeader("Capture Dashboard", "Overview of the traffic capture.");
						}
					});
		} else if (place instanceof TrafficTimeLinePlace) {
			GWT.log("Request for traffic time line place: "
					+ ((TrafficTimeLinePlace) place).getTrafficCaptureId());
			clientFactory
					.getRequestFactory()
					.trafficCaptureRequest()
					.findTrafficCapture(
							((TrafficTimeLinePlace) place)
									.getTrafficCaptureId())
					.with("appVersion.id", "appVersion.versionCode",
							"appVersion.app.appName", "appVersion.app.id")
					.fire(new Receiver<TrafficCaptureProxy>() {

						@Override
						public void onSuccess(TrafficCaptureProxy response) {
							addAppsBreadCrumbEntry();
							addAppBreadcumbEntry(response.getAppVersion()
									.getApp().getId(), response.getAppVersion()
									.getApp().getAppName());
							addAppVersionBreadcrumbEntry(response
									.getAppVersion().getId(), response
									.getAppVersion().getVersionCode()
									.toString());
							addTrafficCaptureBreadcrumbEntry(response.getId(),
									CommonUiUtil.DEFAULT_DATE_TIME_FORMAT
											.format(response.getDateAdded()));
							view.addTextEntry("Traffic timeline");
							view.setHeader("Traffic timeline", "Timeline visualization of traffic.");
						}
					});
		}
		 else if (place instanceof TrafficGeoChartPlace) {
				GWT.log("Request for traffic geochart place: "
						+ ((TrafficGeoChartPlace) place).getTrafficCaptureId());
				clientFactory
						.getRequestFactory()
						.trafficCaptureRequest()
						.findTrafficCapture(
								((TrafficGeoChartPlace) place)
										.getTrafficCaptureId())
						.with("appVersion.id", "appVersion.versionCode",
								"appVersion.app.appName", "appVersion.app.id")
						.fire(new Receiver<TrafficCaptureProxy>() {

							@Override
							public void onSuccess(TrafficCaptureProxy response) {
								addAppsBreadCrumbEntry();
								addAppBreadcumbEntry(response.getAppVersion()
										.getApp().getId(), response.getAppVersion()
										.getApp().getAppName());
								addAppVersionBreadcrumbEntry(response
										.getAppVersion().getId(), response
										.getAppVersion().getVersionCode()
										.toString());
								addTrafficCaptureBreadcrumbEntry(response.getId(),
										CommonUiUtil.DEFAULT_DATE_TIME_FORMAT
												.format(response.getDateAdded()));
								view.addTextEntry("Traffic geochart");
								view.setHeader("Traffic geochart", "Map visualization of traffic.");
							}
						});
			} else if (place instanceof HttpRequestListPlace) {
				GWT.log("Request for http request list place: "
						+ ((HttpRequestListPlace) place).getTrafficCaptureId());
				clientFactory
						.getRequestFactory()
						.trafficCaptureRequest()
						.findTrafficCapture(
								((HttpRequestListPlace) place)
										.getTrafficCaptureId())
						.with("appVersion.id", "appVersion.versionCode",
								"appVersion.app.appName", "appVersion.app.id")
						.fire(new Receiver<TrafficCaptureProxy>() {

							@Override
							public void onSuccess(TrafficCaptureProxy response) {
								addAppsBreadCrumbEntry();
								addAppBreadcumbEntry(response.getAppVersion()
										.getApp().getId(), response.getAppVersion()
										.getApp().getAppName());
								addAppVersionBreadcrumbEntry(response
										.getAppVersion().getId(), response
										.getAppVersion().getVersionCode()
										.toString());
								addTrafficCaptureBreadcrumbEntry(response.getId(),
										CommonUiUtil.DEFAULT_DATE_TIME_FORMAT
												.format(response.getDateAdded()));
								view.addTextEntry("Http Requests");
								view.setHeader("Http Requests", "List of all http requests.");
							}
						});
			}
		 else if (place instanceof AddAppPlace) {
				GWT.log("Request for add app place");
				view.addTextEntry("Add app");
				view.setHeader("Add app", "Add new traffic capture of app.");
			}
	}

	private void addAppsBreadCrumbEntry() {
		addBreadcrumbEntry(new AppListPlace(), "Apps");
	}

	private void addAppBreadcumbEntry(final Long appId, final String text) {
		addBreadcrumbEntry(new AppVersionListPlace(appId), "App (" + text + ")");
	}

	private void addAppVersionBreadcrumbEntry(final Long appVersionId,
			final String text) {
		addBreadcrumbEntry(new TrafficCaptureListPlace(appVersionId),
				"Version (" + text + ")");
	}

	private void addTrafficCaptureBreadcrumbEntry(final Long trafficCaptureId,
			final String text) {
		addBreadcrumbEntry(new CaptureDashboardPlace(trafficCaptureId),
				"Capture (" + text + ")");
	}

	private void addBreadcrumbEntry(final Place place, final String text) {

		view.addLinkEntry(text, clientFactory.getHistoryMapper().getToken(place));
	}

	@Override
	public void bind() {

	}

}
