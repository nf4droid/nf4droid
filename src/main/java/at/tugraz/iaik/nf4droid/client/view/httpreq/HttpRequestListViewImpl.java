package at.tugraz.iaik.nf4droid.client.view.httpreq;

import java.util.List;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.ChartTitle;
import org.moxieapps.gwt.highcharts.client.Exporting;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Style;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.AxisLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.AxisLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabels;
import org.moxieapps.gwt.highcharts.client.labels.XAxisLabels;
import org.moxieapps.gwt.highcharts.client.labels.YAxisLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.BarPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions;

import at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpRequestProxy;
import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;
import at.tugraz.iaik.nf4droid.client.view.common.bootstrap.Span;
import at.tugraz.iaik.nf4droid.client.view.common.event.GetValue;
import at.tugraz.iaik.nf4droid.client.view.common.list.view.AbstractListView;
import at.tugraz.iaik.nf4droid.client.view.common.modal.ModalInfoBoxView;
import at.tugraz.iaik.nf4droid.client.view.common.modal.ModalInfoBoxViewImpl;
import at.tugraz.iaik.nf4droid.client.view.httpreq.request.HttpRequestView;
import at.tugraz.iaik.nf4droid.client.view.httpreq.request.HttpRequestViewImpl;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.BarChartStatisticsEntry;
import at.tugraz.iaik.nf4droid.shared.dto.ClassVariableMapping;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.Section;
import com.github.gwtbootstrap.client.ui.Tooltip;
import com.github.gwtbootstrap.client.ui.Well;
import com.github.gwtbootstrap.client.ui.constants.Placement;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

public class HttpRequestListViewImpl extends
		AbstractListView<DroidHttpRequestProxy> implements HttpRequestListView {

	private static final int MAX_URL_LENGTH = 70;
	private static final String TOOLTIP_EXPOSE_FILTER = "Select filter for http requests which might include exposure of specific data.";
	public static final String FILTER_NONE = "NONE";
	private static final double BAR_CHART_ASPECT_RATIO = 2.2;
	private static final int BAR_CHART_PADDING = 50;
	private static final int BAR_CHART_AXIS_PADDING = 450;
	private static final String TOOLTIP_GROUPING = "Select how the http requests data should be grouped.";
	private ListBox filterListBox;
	private ListBox groupingListBox;
	private Chart httpReqHitStatisticBarChart;
	private Well barChartOptionsArea;

	private HttpRequestListViewImpl() {
		super();
		initFilterOptions();
		initBarChart();
		initBarChartOptions();

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				updateBarChartSize();
			}
		});

	}

	private static class SingletonHolder {
		private static HttpRequestListViewImpl instance = new HttpRequestListViewImpl();
	}

	public static HttpRequestListViewImpl getInstance() {
		return SingletonHolder.instance;
	}

	@Override
	protected Widget createAndBindUi() {
		return super.getUiBinder().createAndBindUi(this);
	}

	class MyTextCell extends TextCell {

		public MyTextCell(SafeHtmlRenderer<String> renderer) {
			super(renderer);
		}
	}

	private static int getOptimalBarChartWidth() {
		return (int) (((Window.getClientWidth() - 2 * CommonUiUtil.FLUID_CONTAINER_PADDING) * CommonUiUtil.FLUID_SPAN_9_ASPECT))
				- (2 * CommonUiUtil.WELL_PADDING);
	}

	private void updateBarChartSize() {
		final int barWidth = getOptimalBarChartWidth();
		final int barHeight = CommonUiUtil.calcHeightFromWidthWithRatio(
				barWidth, BAR_CHART_ASPECT_RATIO);
		httpReqHitStatisticBarChart.setSize(barWidth, barHeight);
	}

	private void initBarChart() {
		final Section section = new Section("urlDomainStatisticBarChart");
		final Heading heading = new Heading(2, "Http Requests Statistics",
				"List of 10 most requested Hosts/URLs/IPs/Countries.");
		final FluidRow fluidRow = new FluidRow();

		final Column column9 = new Column(9);
		final Well barChartWell = new Well();
		final int width = getOptimalBarChartWidth();
		final int height = CommonUiUtil.calcHeightFromWidthWithRatio(width,
				BAR_CHART_ASPECT_RATIO);
		httpReqHitStatisticBarChart = createBarChart("Http Requests Top 10",
				width, height);
		barChartWell.add(httpReqHitStatisticBarChart);
		column9.add(barChartWell);
		fluidRow.add(column9);

		final Column column3 = new Column(3);
		barChartOptionsArea = new Well();
		column3.add(barChartOptionsArea);
		fluidRow.add(column3);

		section.add(heading);
		section.add(fluidRow);
		super.htmlPanel.add(section);
	}

	private Chart createBarChart(final String chartTitle, final int width,
			final int height) {
		final Chart chart = new Chart()
				.setSize(width, height)
				.setMargin(BAR_CHART_PADDING, BAR_CHART_PADDING,
						BAR_CHART_PADDING, BAR_CHART_AXIS_PADDING)
				.setSpacingBottom(0)
				.setSpacingLeft(0)
				.setSpacingRight(0)
				.setSpacingTop(0)
				.setType(Series.Type.BAR)
				.setLegend(new Legend().setEnabled(false))
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
				.setBarPlotOptions(
						new BarPlotOptions()
								.setPointWidth(22)
								.setColorByPoint(true)
								.setAllowPointSelect(false)
								.setCursor(PlotOptions.Cursor.POINTER)
								.setDataLabels(
										new DataLabels().setEnabled(true)))
				.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter() {

					@Override
					public String format(ToolTipData toolTipData) {
						return CommonUiUtil.insertLineWrapsIfLongString(
								toolTipData.getXAsString(), MAX_URL_LENGTH)
								+ ": <b>" + toolTipData.getYAsLong() + "</b>";
					}
				}));

		chart.getXAxis().setLabels(
				new XAxisLabels().setStyle(new Style().setFontWeight("bold"))
						.setFormatter(new AxisLabelsFormatter() {

							@Override
							public String format(AxisLabelsData axisLabelsData) {
								if (axisLabelsData != null) {
									String value = null;
									try {
										value = axisLabelsData
												.getValueAsString();
									} catch(Exception ex) {
										GWT.log("axis label data problem", ex);
									}
									if (value != null) {
										value = CommonUiUtil.limitStringLength(
												value, MAX_URL_LENGTH);
										value = value.replace('-', '\u2011');
									}
									return value;
								}
								return null;
							}
						}));

		chart.getYAxis().setAxisTitleText("Number of requests")
				.setLabels(new YAxisLabels().setStep(2));

		return chart;
	}

	public void clearHttpReqHitStatisticBarChart() {
		httpReqHitStatisticBarChart.removeAllSeries();
	}
	
	public void drawHttpReqHitStatisticBarChart(
			final List<BarChartStatisticsEntry> hitStatisticEntries) {
		httpReqHitStatisticBarChart.showLoading("loading...");
		httpReqHitStatisticBarChart.removeAllSeries();

		if (hitStatisticEntries != null && !hitStatisticEntries.isEmpty() && hitStatisticEntries.get(0).getValue() != null) {
			httpReqHitStatisticBarChart.setVisible(true);
			int i = 0;
			final int entrieCount = hitStatisticEntries.size();
			final String[] categories = new String[entrieCount];
			final Point[] points = new Point[entrieCount];
			for (BarChartStatisticsEntry barChartStatisticsEntry : hitStatisticEntries) {
				categories[i] = barChartStatisticsEntry.getValue();
				points[i] = new Point(barChartStatisticsEntry.getCount());
				i++;
				
			}
			httpReqHitStatisticBarChart.getXAxis().setCategories(categories);
			httpReqHitStatisticBarChart.addSeries(httpReqHitStatisticBarChart
					.createSeries().setPoints(points)
					.setName("Http Requests Top 10"));
			httpReqHitStatisticBarChart.hideLoading();
		} else {
			httpReqHitStatisticBarChart.setVisible(false);
		}
	}

	private void initFilterOptions() {

		filterListBox = new ListBox(false);
		filterListBox.addItem("None", FILTER_NONE);
		filterListBox.setSelectedValue(FILTER_NONE);
		for (ExposeType exposeType : ExposeType.values()) {
			filterListBox.addItem(exposeType.getDescription(),
					exposeType.name());
		}

		final Tooltip filterTooltip = new Tooltip(TOOLTIP_EXPOSE_FILTER);
		filterTooltip.setPlacement(Placement.RIGHT);
		filterTooltip.add(filterListBox);

		final Well filterWell = new Well();
		filterWell.add(new Span("Expose Filter: "));
		filterWell.add(filterTooltip);

		super.optionsArea.add(filterWell);
	}

	private void initBarChartOptions() {

		groupingListBox = new ListBox(false);
		groupingListBox.addItem("URL", ClassVariableMapping.HTTP_REQ_URL);
		groupingListBox.addItem("Host", ClassVariableMapping.HTTP_REQ_HOST);
		groupingListBox.addItem("Remote IP",
				ClassVariableMapping.HTTP_REQ_REMOTE_IP);
		groupingListBox.addItem("Country",
				ClassVariableMapping.HTTP_REQ_COUNTRY);
		groupingListBox.setSelectedValue(ClassVariableMapping.HTTP_REQ_URL);

		final Tooltip groupingTooltip = new Tooltip(TOOLTIP_GROUPING);
		groupingTooltip.setPlacement(Placement.LEFT);
		groupingTooltip.add(groupingListBox);

		barChartOptionsArea.add(new Span("Grouping: "));
		barChartOptionsArea.add(groupingTooltip);

	}

	public HandlerRegistration addFilterChangeHandler(
			final ChangeHandler handler) {
		return filterListBox.addChangeHandler(handler);
	}

	public HandlerRegistration addGroupingChangeHandler(
			final ChangeHandler handler) {
		return groupingListBox.addChangeHandler(handler);
	}

	@Override
	protected void initTableColumns() {

		super.addColumn("URL", new TextCell(),
				new GetValue<DroidHttpRequestProxy, String>() {
					public String getValue(DroidHttpRequestProxy object) {
						String url = object.getUrl();

						if (url != null && !url.isEmpty()) {
							if (url.contains("?")) {
								url = url.split("\\?")[0];
							}
							url = CommonUiUtil.limitStringLength(url,
									MAX_URL_LENGTH);
						}

						return url;
					}
				}, ClassVariableMapping.HTTP_REQ_URL, 48.0);
		super.addColumn("Method", new TextCell(),
				new GetValue<DroidHttpRequestProxy, String>() {
					public String getValue(DroidHttpRequestProxy object) {
						return object.getHttpMethod().toString();
					}
				}, ClassVariableMapping.HTTP_REQ_METHOD, 7.0);

		super.addColumn("Local Port", new TextCell(),
				new GetValue<DroidHttpRequestProxy, String>() {
					public String getValue(DroidHttpRequestProxy object) {
						return object.getLocalPort().toString();
					}
				}, ClassVariableMapping.HTTP_REQ_LOCAL_PORT, 9.0);
		super.addColumn("Remote Port", new TextCell(),
				new GetValue<DroidHttpRequestProxy, String>() {
					public String getValue(DroidHttpRequestProxy object) {
						return object.getRemotePort().toString();
					}
				}, ClassVariableMapping.HTTP_REQ_REMOTE_PORT, 10.0);
		super.addColumn("Remote IP", new TextCell(),
				new GetValue<DroidHttpRequestProxy, String>() {
					public String getValue(DroidHttpRequestProxy object) {
						return object.getRemoteIp();
					}
				}, ClassVariableMapping.HTTP_REQ_REMOTE_IP, 12.0);
		super.addColumn("Country", new TextCell(),
				new GetValue<DroidHttpRequestProxy, String>() {
					public String getValue(DroidHttpRequestProxy object) {
						return object.getRemoteCountry();
					}
				}, ClassVariableMapping.HTTP_REQ_COUNTRY, 14.0);

	}

	public void showHttpRequestView(final String localIp,
			final String remoteIp, final String localPort,
			final String remotePort, final String remoteCountry,
			final String remoteCity, final String method, final String version,
			final String url, final String headerFields,
			final String httpParameters) {

		final HttpRequestView httpRequestView = new HttpRequestViewImpl();
		httpRequestView.setData(localIp, remoteIp, localPort, remotePort,
				remoteCountry, remoteCity, method, version, url, headerFields,
				httpParameters);

		final ModalInfoBoxView modalBox = ModalInfoBoxViewImpl.getInstance();
		modalBox.init("Http request", httpRequestView);
		modalBox.show();
	}

	@Override
	public String getFilterValue() {
		return filterListBox.getValue();
	}

	@Override
	public String getGrouping() {
		return groupingListBox.getValue();
	}

}
