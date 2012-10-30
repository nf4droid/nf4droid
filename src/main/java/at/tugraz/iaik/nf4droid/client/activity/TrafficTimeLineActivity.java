package at.tugraz.iaik.nf4droid.client.activity;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpHeaderFieldProxy;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpParameterProxy;
import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpRequestProxy;
import at.tugraz.iaik.nf4droid.client.view.timeline.TrafficTimeLineView;
import at.tugraz.iaik.nf4droid.client.view.timeline.addseries.AddFilteredSeriesEvent;
import at.tugraz.iaik.nf4droid.client.view.timeline.addseries.AddFilteredSeriesEventHandler;
import at.tugraz.iaik.nf4droid.client.view.timeline.pointclick.SeriesPointClickEvent;
import at.tugraz.iaik.nf4droid.client.view.timeline.pointclick.SeriesPointClickEventHandler;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring;
import at.tugraz.iaik.nf4droid.shared.constant.ExposePoint;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.Criteria;
import at.tugraz.iaik.nf4droid.shared.dto.Expose;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficTimeLineData;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficTimelineDataSet;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class TrafficTimeLineActivity extends BaseActivity {

	// TODO make dynamically adjustable by user, currently the interval is calculated on server 
	private static final int CHART_INTERVAL_IN_MS = -1;
	
	private TrafficTimeLineView view;
	private Long trafficCaptureId;
	private ListDataProvider<Expose> exposeDataProvider;
	
	public TrafficTimeLineActivity(final TrafficTimeLineView view,
			final ClientFactory clientFactory, final Long trafficCaptureId) {
		super(clientFactory);
		this.trafficCaptureId = trafficCaptureId;
		this.view = view;
		this.exposeDataProvider = new ListDataProvider<Expose>();
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.clearTimelineChart();
		view.hideMessage();
		panel.setWidget(view.asWidget());

		view.goToFirstPageOfExposeTable();
		exposeDataProvider.getList().clear();
		exposeDataProvider.addDataDisplay(view.getExposeTable());

		addTotalTrafficLine(CHART_INTERVAL_IN_MS);
		addHttpRequestTrafficAndExpose(CHART_INTERVAL_IN_MS);

		super.start(panel, eventBus);
	}

	@Override
	public void onStop() {
		exposeDataProvider.removeDataDisplay(view.getExposeTable());
		super.onStop();
	}

	private void addHttpRequestTrafficAndExpose(final int intervalInMs) {
		view.showTimelineChartLoadingIndicator();

		clientFactory.getTimelineDataService()
				.getTrafficTimelineHttpRequestDataset(trafficCaptureId,
						intervalInMs,
						new AsyncCallback<TrafficTimelineDataSet>() {

							@Override
							public void onSuccess(TrafficTimelineDataSet result) {
								if ((result != null)
										&& (result.getTimelineData() != null)) {
									final TrafficTimeLineData data = result
											.getTimelineData();
									final String id = view.addAreaSeries(
											data.getValueList(),
											"Http Requests",
											data.getIntervalInMs(),
											data.getStartTime(),
											data.getEndTime(), false);
									final List<Expose> expose = result.getEpxose();
									
									// add expose data to table view
									if (expose != null) {
										GWT.log("Nr of exposes: "+expose.size());
										view.addFlagSeries(expose, id);
										//TODO load data seperatly from server
										exposeDataProvider.getList().addAll(
												expose);

										ListHandler<Expose> columnSortHandler = new ListHandler<Expose>(
												exposeDataProvider.getList());
		
										view.addColumnSortEventHandlerForExposeTable(columnSortHandler);

									} else {
										GWT.log("No http request expose data available.");
									}
								} else {
									GWT.log("No http request data available.");
									view.showMessage(AlertType.INFO, "Info", "No HTTP request data available.");
								}

								view.hideTimelineChartLoadingIndicator();
							}

							@Override
							public void onFailure(Throwable caught) {
								view.showMessage(AlertType.ERROR, "Error",
										"Couldn't retrieve data for http request traffic timeline and expose.");
								GWT.log("Couldn't retrieve data for http request traffic timeline and expose.",
										caught);
							}
						});
	}

	private void addTotalTrafficLine(final int intervalInMs) {
		view.showTimelineChartLoadingIndicator();

		clientFactory.getTimelineDataService().getTotalTrafficTimelineData(
				trafficCaptureId, CHART_INTERVAL_IN_MS, new AsyncCallback<TrafficTimeLineData>() {

					@Override
					public void onSuccess(TrafficTimeLineData result) {
						if (result != null) {
							view.addAreaSeries(result.getValueList(),
									"Total traffic", result.getIntervalInMs(),
									result.getStartTime(), result.getEndTime(),
									false);
						} else {
							view.showMessage(AlertType.ERROR, "Error",
									"Sorry, no network traffic information available.");
							GWT.log("No network traffic information available.");
						}

						view.hideTimelineChartLoadingIndicator();
					}

					@Override
					public void onFailure(Throwable caught) {
						view.showMessage(AlertType.ERROR, "Error",
								"Couldn't retrieve data for total traffic timeline.");
						GWT.log("Couldn't retrieve data for total traffic timeline.",
								caught);
					}
				});
	}

	public void showExposeInfoView(final ExposeType exposeType,
			final ExposeObscuring exposeObscuring,
			final ExposePoint exposePoint, final String exposedValue,
			Long httpReqId) {

		clientFactory.getRequestFactory().httpRequestRequest()
				.findDroidHttpRequest(httpReqId)
				.with("headerFields", "parameters")
				.fire(new Receiver<DroidHttpRequestProxy>() {

					@Override
					public void onSuccess(DroidHttpRequestProxy response) {
						String headerFields = "";
						String httpParameters = "";

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

						view.showExposeInfoView(exposeType, exposeObscuring,
								exposePoint, exposedValue, response
										.getLocalIp(), response.getRemoteIp(),
								response.getLocalPort().toString(), response
										.getRemotePort().toString(), response
										.getRemoteCountry(), response
										.getRemoteCity(), response.getUrl(),
								headerFields, httpParameters);

					}

					@Override
					public void onFailure(ServerFailure error) {
						view.showMessage(AlertType.ERROR, "Error", "Couldn't load HTTP request data related to data expose.");
						GWT.log("Couldn't load HTTP request data related to data expose. - "+error.getExceptionType());
						super.onFailure(error);
					}
					
					

				});

	}

	@Override
	public void bind() {
		registerHandler(view.getAddFilteredSeriesButton().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						view.showAddFilteredSeriesView();
					}
				}));

		registerHandler(view
				.addSeriesPointClickHandler(new SeriesPointClickEventHandler() {

					@Override
					public void onPointClick(final SeriesPointClickEvent event) {
						showExposeInfoView(event.getExposeType(),
								event.getExposeObscuring(),
								event.getExposePoint(),
								event.getExposedValue(), event.getHttpReqId());
					}
				}));

		registerHandler(view
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						if (event != null
								&& event.getSource() instanceof NoSelectionModel<?>) {

							@SuppressWarnings("unchecked")
							final Expose expose = ((NoSelectionModel<Expose>) event
									.getSource()).getLastSelectedObject();

							showExposeInfoView(expose.getExposeType(),
									expose.getExposeObscuring(),
									expose.getExposePoint(),
									expose.getExposedData(),
									expose.getHttpReqId());
						} else {
							GWT.log("Error: Invalid type of selected entry.");
						}

					}
				}));

		registerHandler(view
				.addAddFilteredSeriesHandler(new AddFilteredSeriesEventHandler() {

					@Override
					public void onAddFilteredSeries(AddFilteredSeriesEvent event) {
						view.showTimelineChartLoadingIndicator();

						final Criteria criterias = event.getIpCriterias()
								.toCriteria();

						clientFactory
								.getTimelineDataService()
								.getTrafficTimelineDataForIpv4ByCriteria(
										trafficCaptureId,
										CHART_INTERVAL_IN_MS,
										criterias,
										new AsyncCallback<TrafficTimeLineData>() {

											@Override
											public void onFailure(
													Throwable caught) {
												view.showMessage(
														AlertType.ERROR,
														"Error",
														"Couldn't retrieve data for filtered traffic timeline.");
												GWT.log("Couldn't retrieve data for filtered traffic timeline.",
														caught);
											}

											@Override
											public void onSuccess(
													TrafficTimeLineData result) {
												if (result != null) {
													view.addAreaSeries(
															result.getValueList(),
															"IPv4 with criteria",
															result.getIntervalInMs(),
															result.getStartTime(),
															result.getEndTime(),
															true);
												} else {
													view.showMessage(
															AlertType.WARNING,
															"Info",
															"Sorry, no IPv4 traffic information available for the given criteria.");
													GWT.log("No ipv4 traffic information available for criteria.");
												}
												view.hideTimelineChartLoadingIndicator();
											}

										});
					}
				}));
	}
}
