package at.tugraz.iaik.nf4droid.client.view.dashboard;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.dto.BarChartExposeEntry;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.IsWidget;

public interface CaptureDashboardView extends IsWidget {

	public void setAppName(final String appName);

	public void setAppPackage(final String appPackage);

	public void setAppVersionName(final String appVersionName);

	public void setTotalTraffic(final String totalTraffic);
	
	public void setExposureCount(final Long exposureCount);

	public void setAppVersionCode(final String appVersionCode);

	public void setAppMarketLink(final String link);

	public void drawTrafficRatioForNetworkLayerPie(
			final List<TrafficAmountInfo> trafficAmounts);

	public void drawTrafficRatioForTransportLayerPie(
			final List<TrafficAmountInfo> trafficAmounts);

	public void drawTrafficRatioForPortsPie(
			final List<TrafficAmountInfo> trafficAmounts);
	
	public void drawDataExposureBarChart(final List<BarChartExposeEntry> dataExposures);

	public HasClickHandlers getTrafficTimelineLink();

	public HasClickHandlers getCountryMapLink();

	public HasClickHandlers getHttpRequestsLink();

	public void setTrafficTimelineLink(final String targetHistoryToken);

	public void setCountryMapLink(final String targetHistoryToken);

	public void setHttpRequestsLink(final String targetHistoryToken);
	
	public void showMessage(AlertType alertType, final String heading, final String message);
	public void hideMessage();
}
