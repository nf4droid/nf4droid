package at.tugraz.iaik.nf4droid.client.view.dashboard;

import java.util.List;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.ChartTitle;
import org.moxieapps.gwt.highcharts.client.Credits;
import org.moxieapps.gwt.highcharts.client.Exporting;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Style;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.Labels;
import org.moxieapps.gwt.highcharts.client.labels.PieDataLabels;
import org.moxieapps.gwt.highcharts.client.labels.XAxisLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.ColumnPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;
import at.tugraz.iaik.nf4droid.client.view.common.bootstrap.Span;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartExposeEntry;
import at.tugraz.iaik.nf4droid.shared.dto.TrafficAmountInfo;

import com.github.gwtbootstrap.client.ui.AlertBlock;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.Well;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.github.gwtbootstrap.client.ui.constants.LabelType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CaptureDashboardViewImpl extends Composite implements
		CaptureDashboardView {

	private static final double PIE_CHART_ASPECT_RATIO = 1.3;
	private static final double BAR_CHART_ASPECT_RATIO = 3.2;

	@UiField
	protected Well leftPieChartArea;

	@UiField
	protected Well centerPieChartArea;

	@UiField
	protected Well rightPieChartArea;
	
	@UiField
	protected Well dataExposureBarChartArea;

	@UiField
	protected Span appNameField;

	@UiField
	protected Span appPackageField;

	@UiField
	protected Span appVersionNameField;
	
	@UiField
	protected Span appVersionCodeField;

	@UiField
	protected Anchor marketLink;
	
	@UiField
	protected Span totalTrafficField;

	@UiField
	NavLink trafficTimelineLink;

	@UiField
	NavLink countryMapLink;
	
	@UiField
	NavLink httpRequestsLink;
	
	@UiField
	Label exposureCountLabel;
	
	@UiField
	protected AlertBlock alertBlock;
	
	private Chart trafficAmountRatioLayer3Pie;
	private Chart trafficAmountRatioLayer4Pie;
	private Chart trafficAmountForPortsPie;
	private Chart dataExposureAmountBarChart;

	private static CaptureDashboardViewImplUiBinder uiBinder = GWT
			.create(CaptureDashboardViewImplUiBinder.class);

	interface CaptureDashboardViewImplUiBinder extends
			UiBinder<Widget, CaptureDashboardViewImpl> {
	}

	public CaptureDashboardViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		final int pieWidth = getOptimalPieChartWidth();
		final int pieHeight = CommonUiUtil.calcHeightFromWidthWithRatio(pieWidth, PIE_CHART_ASPECT_RATIO); 

		trafficAmountRatioLayer3Pie = createPieChart("Ratio - Network Layer",
				pieWidth, pieHeight);
		leftPieChartArea.add(trafficAmountRatioLayer3Pie);

		trafficAmountRatioLayer4Pie = createPieChart("Ratio - Transport Layer",
				pieWidth, pieHeight);
		centerPieChartArea.add(trafficAmountRatioLayer4Pie);

		trafficAmountForPortsPie = createPieChart("Port ratio - IP & UDP", pieWidth,
				pieHeight);
		rightPieChartArea.add(trafficAmountForPortsPie);
		
		final int barWidth = getOptimalBarChartWidth();
		final int barHeight = CommonUiUtil.calcHeightFromWidthWithRatio(barWidth, BAR_CHART_ASPECT_RATIO);
		
		dataExposureAmountBarChart = createBarChart("Possible Data Exposure", barWidth, barHeight);
		dataExposureBarChartArea.add(dataExposureAmountBarChart);

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				updatePieChartSizes();
				updateBarChartSize();
			}
		});
	}

	private static class SingletonHolder {
		private static CaptureDashboardViewImpl instance = new CaptureDashboardViewImpl();
	}

	public static CaptureDashboardViewImpl getInstance() {
		return SingletonHolder.instance;
	}
	
	private void updatePieChartSizes() {
		final int pieWidth = getOptimalPieChartWidth();
		final int pieHeight = CommonUiUtil.calcHeightFromWidthWithRatio(pieWidth, PIE_CHART_ASPECT_RATIO);

		trafficAmountForPortsPie.setSize(pieWidth, pieHeight);
		trafficAmountRatioLayer3Pie.setSize(pieWidth, pieHeight);
		trafficAmountRatioLayer4Pie.setSize(pieWidth, pieHeight);
	}
	
	private void updateBarChartSize() {
		final int barWidth = getOptimalBarChartWidth();
		final int barHeight = CommonUiUtil.calcHeightFromWidthWithRatio(barWidth, BAR_CHART_ASPECT_RATIO);
		dataExposureAmountBarChart.setSize(barWidth, barHeight);
	}

	private static int getOptimalPieChartWidth() {
		return (int) (((Window.getClientWidth() - 2 * CommonUiUtil.FLUID_CONTAINER_PADDING) * CommonUiUtil.FLUID_SPAN_4_ASPECT) / 3)
				- (2 * CommonUiUtil.WELL_PADDING);
	}

	private static int getOptimalBarChartWidth() {
		return Window.getClientWidth() - (2 * CommonUiUtil.FLUID_CONTAINER_PADDING)
				- (2 * CommonUiUtil.WELL_PADDING) - 10;
	}

	private Chart createBarChart(final String chartTitle, final int width, final int height) {
		final Chart chart = new Chart()
				.setSize(width, height)
				.setMargin(50, 50, 100, 80)
				.setSpacingBottom(0)
				.setSpacingLeft(0)
				.setSpacingRight(0)
				.setSpacingTop(0)
				.setType(Series.Type.COLUMN)
				.setLegend(new Legend()  
                .setEnabled(false))
				.setExporting(
						new Exporting().setEnabled(true).setWidth(1024)
								.setFilename(chartTitle))
				.setTitle(
						new ChartTitle()
								.setText(chartTitle)
								.setAlign(ChartTitle.Align.LEFT)
								.setStyle(
										new Style().setFontSize("18px")
												.setColor("#333")
												.setFontFamily("inherit")
												.setFontWeight("bold")), null)
				.setBackgroundColor("rgb(245,245,245)")
				.setPlotBackgroundColor("rgb(245,245,245)")
				.setPlotBorderWidth(null)
				.setPlotShadow(false)
				.setReflow(true)
				.setColumnPlotOptions(
						new ColumnPlotOptions()
								.setMinPointLength(3)
								.setColorByPoint(true)
								.setAllowPointSelect(false)
								.setCursor(PlotOptions.Cursor.POINTER))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					
					@Override
					public String format(ToolTipData toolTipData) {
						return "<b>"
								+ toolTipData.getXAsString()
								+ "</b>: "
								+ toolTipData.getYAsLong();
					}
				}));
		
		final String[] categories = new String[ExposeType.values().length];
		int i = 0;
		for (ExposeType exposeType : ExposeType.values()) {
			categories[i++] = exposeType.getDescription();
		}
		
		chart.getXAxis().setCategories(categories).setLabels(new XAxisLabels().setRotation(-45).setAlign(Labels.Align.RIGHT));
		
		chart.getYAxis()  
				.setAxisTitleText("Number of (possible) exposures")  
				.setMin(0);
		
		return chart;
	}
	
	public Chart createPieChart(final String chartTitle, final int width,
			final int height) {

		final Chart chart = new Chart()
				.setSize(width, height)
				.setMargin(0, 0, 0, 0)
				.setSpacingBottom(0)
				.setSpacingLeft(0)
				.setSpacingRight(0)
				.setSpacingTop(0)
				.setType(Series.Type.PIE)
				.setExporting(
						new Exporting().setEnabled(true).setWidth(1024)
								.setFilename(chartTitle))
				.setOption("/navigation/buttonOptions/align", "right")
				.setOption("/navigation/buttonOptions/verticalAlign", "bottom")
				.setOption("/navigation/buttonOptions/y", -10)
				.setTitle(
						new ChartTitle()
								.setText(chartTitle)
								.setAlign(ChartTitle.Align.LEFT)
								.setStyle(
										new Style().setFontSize("18px")
												.setColor("#333")
												.setFontFamily("inherit")
												.setFontWeight("bold")), null)
				.setBackgroundColor("rgb(245,245,245)")
				.setPlotBackgroundColor("rgb(245,245,245)")
				.setPlotBorderWidth(null)
				.setPlotShadow(false)
				.setCredits(new Credits().setEnabled(false))
				.setPiePlotOptions(
						new PiePlotOptions()
								.setAllowPointSelect(true)
								.setCursor(PlotOptions.Cursor.POINTER)
								.setShowInLegend(true)
								.setCenter(0.35,0.5)
								.setSize(0.65)
								.setPieDataLabels(
										new PieDataLabels()
												.setEnabled(false)
												.setDistance(15)
												.setConnectorPadding(3)
												.setFormatter(
														new DataLabelsFormatter() {
															public String format(
																	DataLabelsData dataLabelsData) {
																return ""+dataLabelsData.getPercentage();
															}
														})))
				.setLegend(
						new Legend().setLayout(Legend.Layout.VERTICAL)
								.setAlign(Legend.Align.RIGHT)
								.setVerticalAlign(Legend.VerticalAlign.TOP)
								.setX(-7).setY(25)
								.setBorderWidth(0)
								.setEnabled(true))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {
					public String format(ToolTipData toolTipData) {
						return "<b>"
								+ toolTipData.getPointName()
								+ "</b>: "
								+ CommonUiUtil.humanReadableByteCountGwt(
										toolTipData.getYAsLong(), false)
								+ " - "
								+ NumberFormat.getFormat("###.#").format(
										toolTipData.getPercentage()) + "%";
					}
				}));

		return chart;
	}

	public void drawTrafficRatioForPortsPie(
			final List<TrafficAmountInfo> trafficAmounts) {
		trafficAmountForPortsPie.removeAllSeries();
		Point[] points = convertTrafficAmountInfoListToPoints(trafficAmounts);
		if (points != null) {
			trafficAmountForPortsPie.addSeries(trafficAmountForPortsPie
					.createSeries().setName("Traffic/Port ratio - IP&UDP")
					.setPoints(points));
		} else {
			GWT.log("no data for TrafficRatioForPortsPie");
		}
	}

	public void drawTrafficRatioForNetworkLayerPie(
			final List<TrafficAmountInfo> trafficAmounts) {
		trafficAmountRatioLayer3Pie.removeAllSeries();
		Point[] points = convertTrafficAmountInfoListToPoints(trafficAmounts);
		if (points != null) {
			trafficAmountRatioLayer3Pie.addSeries(trafficAmountRatioLayer3Pie
					.createSeries().setName("Traffic ratio - Network Layer")
					.setPoints(points));
		} else {
			GWT.log("no data for TrafficRatioForNetworkLayerPie");
		}
	}

	@Override
	public void drawTrafficRatioForTransportLayerPie(
			final List<TrafficAmountInfo> trafficAmounts) {
		trafficAmountRatioLayer4Pie.removeAllSeries();
		Point[] points = convertTrafficAmountInfoListToPoints(trafficAmounts);
		if (points != null) {
			trafficAmountRatioLayer4Pie.addSeries(trafficAmountRatioLayer4Pie
					.createSeries().setName("Traffic ratio - Transport Layer")
					.setPoints(points));
		} else {
			GWT.log("no data for TrafficRatioForTransportLayerPie");
		}
	}

	public void drawDataExposureBarChart(final List<BarChartExposeEntry> dataExposures) {
		dataExposureAmountBarChart.removeAllSeries();
		final int size = ExposeType.values().length;
		final Point[] points = new Point[size];
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point(0);
		}
		
		if (dataExposures != null && !dataExposures.isEmpty()) {
			
			for (BarChartExposeEntry barChartExposeEntry : dataExposures) {
				points[barChartExposeEntry.getExposeType().ordinal()] = new Point(barChartExposeEntry.getValue());
			}
		}	
		dataExposureAmountBarChart.addSeries(dataExposureAmountBarChart.createSeries().setPoints(points).setName("DataExposure"));
	}
	
	private Point[] convertTrafficAmountInfoListToPoints(
			final List<TrafficAmountInfo> trafficAmounts) {
		if ((trafficAmounts != null) && (!trafficAmounts.isEmpty())) {
			Point[] points = new Point[trafficAmounts.size()];
			int i = 0;
			for (TrafficAmountInfo amountInfo : trafficAmounts) {
				points[i] = new Point(amountInfo.getDescription(),
						amountInfo.getTrafficAmount());
				i++;
			}
			return points;
		} else {
			return null;
		}
	}

	public void setAppName(final String appName) {
		appNameField.setText(appName);
	}

	public void setAppPackage(final String appPackage) {
		appPackageField.setText(appPackage);
	}

	@Override
	public void setAppVersionName(final String appVersionName) {
		appVersionNameField.setText(appVersionName);
	}
	
	public void setAppVersionCode(final String appVersionCode) {
		appVersionCodeField.setText(appVersionCode);
	}

	public void setAppMarketLink(final String link) {
		marketLink.setHref(link);
	}
	
	public void setTotalTraffic(final String totalTraffic) {
		totalTrafficField.setText(totalTraffic);
	}
	
	public void setExposureCount(final Long exposureCount) {
		exposureCountLabel.setText(exposureCount.toString());
		if (exposureCount > 0) {
			exposureCountLabel.setType(LabelType.WARNING);
		} else {
			exposureCountLabel.setType(LabelType.SUCCESS);
		}
	}

	public HasClickHandlers getTrafficTimelineLink() {
		return trafficTimelineLink;
	}

	public HasClickHandlers getCountryMapLink() {
		return countryMapLink;
	}
	
	public HasClickHandlers getHttpRequestsLink() {
		return httpRequestsLink;
	}
	
	public void setTrafficTimelineLink(final String targetHistoryToken) {
		trafficTimelineLink.setTargetHistoryToken(targetHistoryToken);
	}
	
	public void setCountryMapLink(final String targetHistoryToken) {
		countryMapLink.setTargetHistoryToken(targetHistoryToken);
	}
	
	public void setHttpRequestsLink(final String targetHistoryToken) {
		httpRequestsLink.setTargetHistoryToken(targetHistoryToken);
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

	public void hideMessage() {
		alertBlock.clear();
		alertBlock.setVisible(false);
	}
}
