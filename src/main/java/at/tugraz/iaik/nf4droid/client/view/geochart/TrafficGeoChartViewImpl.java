package at.tugraz.iaik.nf4droid.client.view.geochart;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;
import at.tugraz.iaik.nf4droid.client.view.common.bootstrap.Span;
import at.tugraz.iaik.nf4droid.client.view.visualizations.GeoChart;
import at.tugraz.iaik.nf4droid.client.view.visualizations.GeoChart.DisplayMode;
import at.tugraz.iaik.nf4droid.client.view.visualizations.GeoChart.Options;
import at.tugraz.iaik.nf4droid.client.view.visualizations.GeoChart.Resolution;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.github.gwtbootstrap.client.ui.AlertBlock;
import com.github.gwtbootstrap.client.ui.Well;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;

public class TrafficGeoChartViewImpl extends Composite implements
		TrafficGeoChartView {

	private static final int LIBRARY_LOAD_WAIT_TIME = 50;

	private static final double GEO_CHART_ASPECT_RATIO = 1.6;

	private static class SingletonHolder {
		private static TrafficGeoChartViewImpl instance = new TrafficGeoChartViewImpl();
	}

	public static TrafficGeoChartViewImpl getInstance() {
		return SingletonHolder.instance;
	}

	@UiField
	protected Well trafficGeoChartArea;

	@UiField
	protected AlertBlock alertBlock;

	@UiField
	protected Span warningText;

	@UiField
	protected Well unknownWarning;

	@UiField
	protected Span totalText;

	private DataTable dataTable;
	private Options options;
	private GeoChart chart;
	private boolean isReady = false;

	private static TrafficGeoChartViewImplUiBinder uiBinder = GWT
			.create(TrafficGeoChartViewImplUiBinder.class);

	interface TrafficGeoChartViewImplUiBinder extends
			UiBinder<Widget, TrafficGeoChartViewImpl> {
	}

	private TrafficGeoChartViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		VisualizationUtils.loadVisualizationApi(new Runnable() {

			@Override
			public void run() {
				isReady = true;
				initGeoChart();

				Window.addResizeHandler(new ResizeHandler() {

					@Override
					public void onResize(ResizeEvent event) {
						updateGeoChartSize();
					}

				});
			}
		}, GeoChart.PACKAGE);
	}

	private void updateGeoChartSize() {
		final int width = getOptimalGeoChartWidth();
		final int height = CommonUiUtil.calcHeightFromWidthWithRatio(width,
				GEO_CHART_ASPECT_RATIO);
		options.setSize(width, height);
		chart.draw(dataTable, options);
	}

	public void drawGeoChart(final List<TrafficAmountInfo> countryTrafficAmounts) {
		// check if Google visualizations library is already loaded
		if (isReady == true) {
			GWT.log("Start drawing geo chart.");
			if (countryTrafficAmounts != null
					&& !countryTrafficAmounts.isEmpty()) {

				initGeoChart();
				dataTable.addRows(countryTrafficAmounts.size());

				int rowIndex = 0;
				for (TrafficAmountInfo countryTrafficAmount : countryTrafficAmounts) {
					dataTable.setValue(rowIndex, 0,
							countryTrafficAmount.getDescription());
					dataTable.setValue(rowIndex, 1,
							countryTrafficAmount.getTrafficAmount());
					rowIndex++;
				}

				chart.draw(dataTable, options);
				GWT.log("Finished drawing geo chart.");
			} else {
				showMessage(AlertType.WARNING, "Info",
						"Sorry, no country traffic information available.");
				GWT.log("Sorry, no country traffic information available.");
			}
		} else {
			GWT.log("Google visualizations library not yet loaded! Waiting and will retry in: "
					+ LIBRARY_LOAD_WAIT_TIME + "ms");
			Timer timer = new Timer() {

				@Override
				public void run() {
					GWT.log("Finished waiting for Google visualizations library! Try drawing again...");
					drawGeoChart(countryTrafficAmounts);
				}
			};
			timer.schedule(LIBRARY_LOAD_WAIT_TIME);
		}
	}

	private void initGeoChart() {
		initDataTable();
		initOptions();

		/**
		 * Fix problem, that chart get's detached/attached if view
		 * disappears/appears, but not redrawn.
		 * 
		 * @see http 
		 *      ://groups.google.com/group/gwt-google-apis/browse_thread/thread
		 *      /4d1efc3fc700f1f4
		 */
		if (chart != null) {
			chart.removeFromParent();
		}

		chart = new GeoChart(dataTable, options);
		trafficGeoChartArea.add(chart);
	}

	private void initDataTable() {
		dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Country");
		dataTable.addColumn(ColumnType.NUMBER, "Traffic amount [Bytes]");
	}

	private void initOptions() {
		options = GeoChart.Options.create();
		options.setResolution(Resolution.COUNTRIES);
		options.setDisplayMode(DisplayMode.REGIONS);
		options.setColorAxis("#99D8C9", "#2CA25F");
		options.setBackgroundColor("whiteSmoke");
		options.setMagnifyingGlass(true);

		final int width = getOptimalGeoChartWidth();
		final int height = CommonUiUtil.calcHeightFromWidthWithRatio(width,
				GEO_CHART_ASPECT_RATIO);
		options.setSize(width, height);

	}

	private int getOptimalGeoChartWidth() {
		return (int) ((Window.getClientWidth() - 2 * CommonUiUtil.FLUID_CONTAINER_PADDING) * CommonUiUtil.FLUID_SPAN_9_ASPECT)
				- 2 * CommonUiUtil.WELL_PADDING;
	}

	public void setTotalTrafficInfo(final Long trafficAmount) {
		totalText.setText("Total IPv4 traffic amount: "
				+ CommonUiUtil.humanReadableByteCountGwt(trafficAmount, false));
	}

	public void showWarningUnknownCountries(final Long trafficAmountUnknown,
			final double percentageUnknown) {
		unknownWarning.setVisible(true);
		warningText
				.setText("The connection country information wasn't available for all IPv4 packets. "
						+ NumberFormat.getFormat("###.##").format(
								percentageUnknown)
						+ "% ("
						+ CommonUiUtil.humanReadableByteCountGwt(
								trafficAmountUnknown, false)
						+ ") of data have been omitted.");
	}

	public void hideWarningUnknownCountries() {
		unknownWarning.setVisible(false);
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

}
