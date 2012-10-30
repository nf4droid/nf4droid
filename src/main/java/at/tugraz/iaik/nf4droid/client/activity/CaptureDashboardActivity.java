package at.tugraz.iaik.nf4droid.client.activity;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.place.HttpRequestListPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficGeoChartPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficTimeLinePlace;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.TrafficCaptureProxy;
import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;
import at.tugraz.iaik.nf4droid.client.view.dashboard.CaptureDashboardView;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartExposeEntry;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class CaptureDashboardActivity extends BaseActivity {
	
	private static final String APP_MARKET_URL = "http://play.google.com/store/apps/details?id=";

	private CaptureDashboardView view;
	private Long trafficCaptureId;
	
	public CaptureDashboardActivity(final CaptureDashboardView view, final ClientFactory clientFactory, final Long trafficCaptureId) {
		super(clientFactory);
		this.view = view;
		this.trafficCaptureId = trafficCaptureId;
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view.asWidget());
		
		view.setTrafficTimelineLink(clientFactory.getHistoryMapper().getToken(new TrafficTimeLinePlace(trafficCaptureId)));
		view.setHttpRequestsLink(clientFactory.getHistoryMapper().getToken(new HttpRequestListPlace(trafficCaptureId)));
		view.setCountryMapLink(clientFactory.getHistoryMapper().getToken(new TrafficGeoChartPlace(trafficCaptureId)));
		
		setAppInformationSummary();
		drawTrafficRatioForNetworkLayerPie();
		drawTrafficRatioForTransportLayerPie();
		drawTrafficRatioForPortsPie();
		drawDataExposureBarChart();

		super.start(panel, eventBus);
	}


	@Override
	public void bind() {

	}
	
	private void drawDataExposureBarChart() {

		clientFactory.getDashboardDataService().findDataExposureAmount(trafficCaptureId, new AsyncCallback<List<BarChartExposeEntry>>() {

			@Override
			public void onFailure(Throwable caught) {
				view.showMessage(AlertType.ERROR, "Error", "Couldn't load data expsoure information.");
				GWT.log("ERROR: drawDataExposureBarChart",caught);
			}

			@Override
			public void onSuccess(List<BarChartExposeEntry> result) {
				view.drawDataExposureBarChart(result);
			}
		});	
		
	}
	
	private void drawTrafficRatioForNetworkLayerPie() {
		clientFactory.getDashboardDataService().getTrafficRatioForNetworkLayer(trafficCaptureId, new AsyncCallback<List<TrafficAmountInfo>>() {
			
			@Override
			public void onSuccess(List<TrafficAmountInfo> result) {
				view.drawTrafficRatioForNetworkLayerPie(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				view.showMessage(AlertType.ERROR, "Error", "Couldn't load traffic ratio for network layer.");
				GWT.log("ERROR: drawTrafficRatioForNetworkLayerPie",caught);
			}
		});
	}
	
	private void drawTrafficRatioForTransportLayerPie() {
		clientFactory.getDashboardDataService().getTrafficRatioForTransportLayer(trafficCaptureId, new AsyncCallback<List<TrafficAmountInfo>>() {
			
			@Override
			public void onSuccess(List<TrafficAmountInfo> result) {
				view.drawTrafficRatioForTransportLayerPie(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				view.showMessage(AlertType.ERROR, "Error", "Couldn't load traffic ratio for transport layer.");
				GWT.log("ERROR: drawTrafficRatioForTransportLayerPie",caught);
			}
		});
	}
	
	private void drawTrafficRatioForPortsPie() {
		clientFactory.getDashboardDataService().getTrafficRatioForPorts(trafficCaptureId, new AsyncCallback<List<TrafficAmountInfo>>() {
			
			@Override
			public void onSuccess(List<TrafficAmountInfo> result) {
				view.drawTrafficRatioForPortsPie(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				view.showMessage(AlertType.ERROR, "Error", "Couldn't load traffic ratio for udp and tcp ports.");
				GWT.log("ERROR: drawTrafficRatioForPortsPie",caught);
			}
		});
	}
	
	
	private void setAppInformationSummary() {
		clientFactory.getRequestFactory().trafficCaptureRequest().findTrafficCapture(trafficCaptureId).with("appVersion.app.appName", "appVersion.app.appPackage", "appVersion.versionName", "appVersion.versionCode").fire(new Receiver<TrafficCaptureProxy>() {


			@Override
			public void onFailure(ServerFailure error) {
				view.showMessage(AlertType.ERROR, "Error", "Couldn't get app information summary.");
				GWT.log("ERROR: setAppInformationSummary - "+error.getExceptionType());
				super.onFailure(error);
			}

			@Override
			public void onSuccess(TrafficCaptureProxy response) {
				view.setAppName(response.getAppVersion().getApp().getAppName());
				view.setAppPackage(response.getAppVersion().getApp().getAppPackage());
				view.setAppVersionName(response.getAppVersion().getVersionName());
				view.setAppVersionCode(response.getAppVersion().getVersionCode().toString());
				view.setAppMarketLink(APP_MARKET_URL+response.getAppVersion().getApp().getAppPackage());
			}
			
			
		});
		
		clientFactory.getDashboardDataService().getTotalTrafficLength(trafficCaptureId, new AsyncCallback<Long>() {
			
			@Override
			public void onSuccess(Long result) {
				if (result != null) {
					view.setTotalTraffic(CommonUiUtil.humanReadableByteCountGwt(result, false));
				} else {
					view.setTotalTraffic("No traffic information available.");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				view.showMessage(AlertType.ERROR, "Error", "Couldn't load total traffic amount info.");
				GWT.log("ERROR: getTotalTrafficLength",caught);
			}
		});
		
		clientFactory.getDashboardDataService().coundDataExposures(trafficCaptureId, new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {
				view.showMessage(AlertType.ERROR, "Error", "Couldn't load data exposure count.");
				GWT.log("ERROR: coundDataExposures",caught);
			}

			@Override
			public void onSuccess(Long result) {
				if (result != null) {
					view.setExposureCount(result);
				}
			}
		});
		
	}


}
