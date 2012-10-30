package at.tugraz.iaik.nf4droid.client.activity.mapper;

import at.tugraz.iaik.nf4droid.client.activity.AddAppActivity;
import at.tugraz.iaik.nf4droid.client.activity.AppListActivity;
import at.tugraz.iaik.nf4droid.client.activity.AppVersionListActivity;
import at.tugraz.iaik.nf4droid.client.activity.CaptureDashboardActivity;
import at.tugraz.iaik.nf4droid.client.activity.HttpRequestListActivity;
import at.tugraz.iaik.nf4droid.client.activity.TrafficCaptureListActivity;
import at.tugraz.iaik.nf4droid.client.activity.TrafficGeoChartActivity;
import at.tugraz.iaik.nf4droid.client.activity.TrafficTimeLineActivity;
import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.place.AddAppPlace;
import at.tugraz.iaik.nf4droid.client.place.AppListPlace;
import at.tugraz.iaik.nf4droid.client.place.AppVersionListPlace;
import at.tugraz.iaik.nf4droid.client.place.CaptureDashboardPlace;
import at.tugraz.iaik.nf4droid.client.place.HttpRequestListPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficCaptureListPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficGeoChartPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficTimeLinePlace;
import at.tugraz.iaik.nf4droid.client.view.addapp.AddAppViewImpl;
import at.tugraz.iaik.nf4droid.client.view.app.AppListViewImpl;
import at.tugraz.iaik.nf4droid.client.view.appversion.AppVersionListViewImpl;
import at.tugraz.iaik.nf4droid.client.view.dashboard.CaptureDashboardViewImpl;
import at.tugraz.iaik.nf4droid.client.view.geochart.TrafficGeoChartViewImpl;
import at.tugraz.iaik.nf4droid.client.view.httpreq.HttpRequestListViewImpl;
import at.tugraz.iaik.nf4droid.client.view.timeline.TrafficTimeLineViewImpl;
import at.tugraz.iaik.nf4droid.client.view.trafficcap.TrafficCaptureListViewImpl;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;

public class MainContentActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public MainContentActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		GWT.log("Looking up activity for place: " + place.toString());

		if (place instanceof AppListPlace) {
			return new AppListActivity(AppListViewImpl.getInstance(),
					clientFactory);
		} else if (place instanceof AppVersionListPlace) {
			return new AppVersionListActivity(
					AppVersionListViewImpl.getInstance(), clientFactory,
					((AppVersionListPlace) place).getAppId());
		} else if (place instanceof TrafficCaptureListPlace) {
			return new TrafficCaptureListActivity(
					TrafficCaptureListViewImpl.getInstance(), clientFactory,
					((TrafficCaptureListPlace) place).getAppVersionId());
		} else if (place instanceof TrafficGeoChartPlace) {
			return new TrafficGeoChartActivity(
					TrafficGeoChartViewImpl.getInstance(), clientFactory,
					((TrafficGeoChartPlace) place).getTrafficCaptureId());
		} else if (place instanceof CaptureDashboardPlace) {
			return new CaptureDashboardActivity(
					CaptureDashboardViewImpl.getInstance(), clientFactory,
					((CaptureDashboardPlace) place).getTrafficCaptureId());
		} else if (place instanceof TrafficTimeLinePlace) {
			return new TrafficTimeLineActivity(
					TrafficTimeLineViewImpl.getInstance(), clientFactory,
					((TrafficTimeLinePlace) place).getTrafficCaptureId());
		} else if (place instanceof HttpRequestListPlace) {
			return new HttpRequestListActivity(
					HttpRequestListViewImpl.getInstance(), clientFactory,
					((HttpRequestListPlace) place).getTrafficCaptureId());
		} else if (place instanceof AddAppPlace) {
			return new AddAppActivity(AddAppViewImpl.getInstance(),
					clientFactory);
		}
		return null;
	}
}
