package at.tugraz.iaik.nf4droid.client.view.httpreq;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpRequestProxy;
import at.tugraz.iaik.nf4droid.client.view.common.event.HasFilter;
import at.tugraz.iaik.nf4droid.client.view.common.event.HasGrouping;
import at.tugraz.iaik.nf4droid.client.view.common.list.view.ListView;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartStatisticsEntry;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public interface HttpRequestListView extends ListView<DroidHttpRequestProxy>, HasFilter, HasGrouping {
	public void showHttpRequestView(final String localIp,
			final String remoteIp, final String localPort,
			final String remotePort, final String remoteCountry,
			final String remoteCity, final String method, final String version,
			final String url, final String headerFields,
			final String httpParameters);
	
	public HandlerRegistration addFilterChangeHandler(final ChangeHandler handler);
	
	public HandlerRegistration addGroupingChangeHandler(final ChangeHandler handler);
	
	public void drawHttpReqHitStatisticBarChart(
			final List<BarChartStatisticsEntry> hitStatisticEntries);
	
	public void clearHttpReqHitStatisticBarChart();
}
