package at.tugraz.iaik.nf4droid.client.activity;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpHeaderFieldProxy;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpParameterProxy;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpRequestProxy;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.request.DetailDroidHttpReqRequest;
import at.tugraz.iaik.nf4droid.client.view.common.list.ListAsyncDataProvider;
import at.tugraz.iaik.nf4droid.client.view.httpreq.HttpRequestListView;
import at.tugraz.iaik.nf4droid.client.view.httpreq.HttpRequestListViewImpl;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartStatisticsEntry;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class HttpRequestListActivity extends
		AbstractListActivity<DroidHttpRequestProxy> {

	private Long trafficCaptureId;
	private HttpRequestListView view;

	public HttpRequestListActivity(HttpRequestListView view,
			ClientFactory clientFactory, Long trafficCaptureId) {
		super(view, clientFactory);
		this.view = view;
		this.trafficCaptureId = trafficCaptureId;
	}

	public void bind() {
		registerHandler(view.addFilterChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				updateView();
				
			}
		}));
		
		registerHandler(view.addGroupingChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				drawHttpReqHitStatisticBarChart();
			}
		}));
		
		super.bind();
	}

	protected void updateView() {
		super.updateView();
		drawHttpReqHitStatisticBarChart();
	}
	
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.clearHttpReqHitStatisticBarChart();
		drawHttpReqHitStatisticBarChart();
		super.start(panel, eventBus);
		
	}

	private void drawHttpReqHitStatisticBarChart() {
		clientFactory
				.getGeneralDataService()
				.getHttpRequestsHitStatistics(trafficCaptureId,
						view.getSearchString(), 0, 10, filterValueToExposType(view.getFilterValue()),
						view.getGrouping(), new AsyncCallback<List<BarChartStatisticsEntry>>() {
							
							@Override
							public void onSuccess(List<BarChartStatisticsEntry> result) {
								view.drawHttpReqHitStatisticBarChart(result);
							}
							
							@Override
							public void onFailure(Throwable caught) {
								view.showMessage(AlertType.ERROR, "Error", "Couldn't load HTTP request hit statistic.");
								GWT.log("Error: drawHttpReqHitStatisticBarChart", caught);
							}
						});
	}

	@Override
	public void initDataProvider() {
		dataProvider = new ListAsyncDataProvider<DroidHttpRequestProxy>() {

			@Override
			public void retrieveData(Range range, String searchString,
					List<String> orderByProperties,
					List<Boolean> orderByValues, DataReceiver receiver) {

				final ExposeType exposeFilter = getFilterValue();
				
				if (exposeFilter != null) {
					final DetailDroidHttpReqRequest httpReqRequest = clientFactory
							.getRequestFactory().detailHttpReqRequest();
					httpReqRequest
							.findHttpRequestsForCaptureByFieldsLikeFilterLimitOrderBy(
									trafficCaptureId, searchString,
									range.getStart(), range.getLength(),
									orderByProperties, orderByValues,
									exposeFilter).fire(receiver);
				} else {

					final DetailDroidHttpReqRequest httpReqRequest = clientFactory
							.getRequestFactory().detailHttpReqRequest();
					httpReqRequest
							.findHttpRequestsForCaptureByFieldsLikeLimitOrderBy(
									trafficCaptureId, searchString,
									range.getStart(), range.getLength(),
									orderByProperties, orderByValues).fire(
									receiver);
				}
			}

			@Override
			public void countData(String searchString, CountReceiver receiver) {
				final ExposeType exposeFilter = getFilterValue();
				
				if (exposeFilter != null) {
					final DetailDroidHttpReqRequest httpReqRequest = clientFactory
							.getRequestFactory().detailHttpReqRequest();
					httpReqRequest
							.countHttpRequestsForCaptureByFieldsLikeFilter(
									trafficCaptureId, searchString,
									exposeFilter).fire(receiver);
				} else {
					final DetailDroidHttpReqRequest httpReqRequest = clientFactory
							.getRequestFactory().detailHttpReqRequest();
					httpReqRequest.countHttpRequestsForCaptureByFieldsLike(
							trafficCaptureId, searchString).fire(receiver);
				}
			}
		};
	}
	
	private ExposeType getFilterValue() {
		if (view != null) {
			return filterValueToExposType(view
					.getFilterValue());
		} else {
			return null;
		}
	}

	private ExposeType filterValueToExposType(final String filterValue) {
		if (filterValue != null
				&& !filterValue.equals(HttpRequestListViewImpl.FILTER_NONE)) {
			return ExposeType.valueOf(filterValue);
		} else {
			return null;
		}
	}

	@Override
	protected void onListEntrySelected(DroidHttpRequestProxy proxy) {

		if (proxy != null && proxy.getId() != null) {
			clientFactory.getRequestFactory().httpRequestRequest()
					.findDroidHttpRequest(proxy.getId())
					.with("headerFields", "parameters")
					.fire(new Receiver<DroidHttpRequestProxy>() {

						@Override
						public void onSuccess(DroidHttpRequestProxy response) {
							String headerFields = null;
							final List<DroidHttpHeaderFieldProxy> headerFieldsList = response
									.getHeaderFields();
							if (headerFieldsList != null
									&& !headerFieldsList.isEmpty()) {
								final StringBuilder strb = new StringBuilder();
								for (DroidHttpHeaderFieldProxy headerField : headerFieldsList) {
									strb.append(headerField.getFieldName());
									strb.append(":");
									strb.append(headerField.getFieldValue());
									strb.append(";  ");
								}
								headerFields = strb.toString();
							}

							String httpParameters = null;
							final List<DroidHttpParameterProxy> parameters = response
									.getParameters();
							if (parameters != null && !parameters.isEmpty()) {
								final StringBuilder strb = new StringBuilder();
								for (DroidHttpParameterProxy parameter : parameters) {
									strb.append(parameter.getParameterName());
									strb.append(":");
									strb.append(parameter.getParameterValue());
									strb.append(";  ");
								}
								httpParameters = strb.toString();
							}

							GWT.log("Show HTTP request modal box view.");

							view.showHttpRequestView(response.getRemoteIp(),
									response.getRemotePort().toString(),
									response.getLocalIp(), response
											.getLocalPort().toString(),
									response.getRemoteCountry(), response
											.getRemoteCity(), response
											.getHttpMethod().toString(),
									response.getHttpVersion().toString(),
									response.getUrl(), headerFields,
									httpParameters);

						}
					});
		} else {
			view.showMessage(AlertType.ERROR, "Error", "Couldn't load HTTP request info.");
			GWT.log("Couldn't load HTTP request info for selected entry.");
		}

	}

}
