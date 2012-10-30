package at.tugraz.iaik.nf4droid.client.activity;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.view.geochart.TrafficGeoChartView;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class TrafficGeoChartActivity extends BaseActivity {

	private TrafficGeoChartView view;
	private Long trafficCaptureId;

	public TrafficGeoChartActivity(final TrafficGeoChartView view,
			final ClientFactory clientFactory, final Long trafficCaptureId) {
		super(clientFactory);
		this.view = view;
		this.trafficCaptureId = trafficCaptureId;
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.hideWarningUnknownCountries();

		panel.setWidget(view.asWidget());

		drawGeoChart();
		setTrafficAmountInfo();

		super.start(panel, eventBus);
	}

	private void drawGeoChart() {
		clientFactory.getGeneralDataService().getIpv4CountryTrafficAmount(
				trafficCaptureId, new AsyncCallback<List<TrafficAmountInfo>>() {

					@Override
					public void onSuccess(List<TrafficAmountInfo> result) {
						view.drawGeoChart(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						view.showMessage(AlertType.ERROR, "Error",
								"Couldn't retrieve geo chart traffic data.");
						GWT.log("Couldn't retrieve geo chart traffic data.",
								caught);
					}
				});
	}

	private void setTrafficUnknownInfo(
			final TrafficAmountInfo totalTrafficAmount) {
		clientFactory.getGeneralDataService()
				.getIpv4CountryUnknownTrafficAmount(trafficCaptureId,
						new AsyncCallback<TrafficAmountInfo>() {

							@Override
							public void onSuccess(TrafficAmountInfo unkownInfo) {
								if (unkownInfo != null) {
									view.showWarningUnknownCountries(unkownInfo
											.getTrafficAmount(), unkownInfo
											.getTrafficAmount().doubleValue()
											/ totalTrafficAmount
													.getTrafficAmount()
													.doubleValue() * 100.0);
									GWT.log("Country information not available for parts of the traffic");
								} else {
									GWT.log("Country information available for the complete traffic.");
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								view.showMessage(AlertType.ERROR, "Error",
										"Couldn't retrieve geo chart unknown traffic info.");
								GWT.log("Couldn't retrieve geo chart unknown traffic info.",
										caught);
							}
						});
	}

	private void setTrafficAmountInfo() {

		clientFactory.getGeneralDataService().getIpv4TotalTrafficAmount(
				trafficCaptureId, new AsyncCallback<TrafficAmountInfo>() {

					@Override
					public void onSuccess(final TrafficAmountInfo totalInfo) {
						if (totalInfo != null
								&& totalInfo.getTrafficAmount() != null) {
							view.setTotalTrafficInfo(totalInfo
									.getTrafficAmount());
							setTrafficUnknownInfo(totalInfo);
						} else {
							view.setTotalTrafficInfo(0L);
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						view.showMessage(AlertType.ERROR, "Error",
								"Couldn't retrieve geo chart total traffic info.");
						GWT.log("Couldn't retrieve geo chart total traffic info.",
								caught);
					}
				});

	}

	@Override
	public void bind() {

	}

}
