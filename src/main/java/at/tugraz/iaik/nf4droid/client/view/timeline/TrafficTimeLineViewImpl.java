package at.tugraz.iaik.nf4droid.client.view.timeline;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.moxieapps.gwt.highcharts.client.Axis;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Chart.ZoomType;
import org.moxieapps.gwt.highcharts.client.ChartSubtitle;
import org.moxieapps.gwt.highcharts.client.ChartTitle;
import org.moxieapps.gwt.highcharts.client.Exporting;
import org.moxieapps.gwt.highcharts.client.Loading;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Series.Type;
import org.moxieapps.gwt.highcharts.client.Style;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.XAxis;
import org.moxieapps.gwt.highcharts.client.YAxis;
import org.moxieapps.gwt.highcharts.client.events.PointClickEvent;
import org.moxieapps.gwt.highcharts.client.events.PointClickEventHandler;
import org.moxieapps.gwt.highcharts.client.labels.AxisLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.AxisLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.YAxisLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.AreaPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.Marker;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions.Cursor;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;
import at.tugraz.iaik.nf4droid.client.view.common.bootstrap.Span;
import at.tugraz.iaik.nf4droid.client.view.common.event.GetValue;
import at.tugraz.iaik.nf4droid.client.view.common.list.CellTableProgressBar;
import at.tugraz.iaik.nf4droid.client.view.common.list.FixedSimplePager;
import at.tugraz.iaik.nf4droid.client.view.common.list.SortableColumn;
import at.tugraz.iaik.nf4droid.client.view.common.modal.ModalInfoBoxView;
import at.tugraz.iaik.nf4droid.client.view.common.modal.ModalInfoBoxViewImpl;
import at.tugraz.iaik.nf4droid.client.view.timeline.addseries.AddFilteredSeriesEvent;
import at.tugraz.iaik.nf4droid.client.view.timeline.addseries.AddFilteredSeriesEventHandler;
import at.tugraz.iaik.nf4droid.client.view.timeline.addseries.AddFilteredSeriesView;
import at.tugraz.iaik.nf4droid.client.view.timeline.addseries.AddFilteredSeriesViewImpl;
import at.tugraz.iaik.nf4droid.client.view.timeline.expose.ExposeInfoView;
import at.tugraz.iaik.nf4droid.client.view.timeline.expose.ExposeInfoViewImpl;
import at.tugraz.iaik.nf4droid.client.view.timeline.pointclick.SeriesPointClickEvent;
import at.tugraz.iaik.nf4droid.client.view.timeline.pointclick.SeriesPointClickEventHandler;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring;
import at.tugraz.iaik.nf4droid.shared.constant.ExposePoint;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.Expose;
import at.tugraz.iaik.nf4droid.shared.dto.TimeValuePair;

import com.github.gwtbootstrap.client.ui.AlertBlock;
import com.github.gwtbootstrap.client.ui.Badge;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.ModalFooter;
import com.github.gwtbootstrap.client.ui.NavWidget;
import com.github.gwtbootstrap.client.ui.Pagination;
import com.github.gwtbootstrap.client.ui.Well;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.github.gwtbootstrap.client.ui.constants.BackdropType;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconSize;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.github.gwtbootstrap.client.ui.resources.ButtonSize;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.RowCountChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;

public class TrafficTimeLineViewImpl extends Composite implements
		TrafficTimeLineView {

	private static final double TIMELINE_CHART_ASPECT_RATIO = 2.2;
	private static final String LOADING_MSG = "loading...";
	private static final boolean SHOW_DEFAULT_LEGEND = false;
	private static final int EXPOSE_TABLE_PAGE_SIZE = 10;
	private static final int FAST_FORWARD_ROWS = 10;
	private static final boolean FAST_FORWARD_BUTTON = true;
	private static final boolean LAST_PAGE_BUTTON = true;
	protected static final String ROW_COUNT_INFO_PREFIX = "Total: ";

	@UiField
	protected Well trafficTimelineChartArea;

	@UiField
	protected AlertBlock alertBlock;

	@UiField
	protected FlexTable legendTable;

	@UiField
	protected Button addFilteredSeriesButton;

	@UiField(provided = true)
	protected CellTable<Expose> exposeTable;

	@UiField
	protected Pagination exposeTablePagination;

	@UiField
	protected Span exposeRowCountInfo;

	private SimplePager exposeTablePager;
	private NoSelectionModel<Expose> exposeSelectionModel;

	private Chart trafficTimelineChart;
	private SeriesPlotOptions trafficTimeLinePlotOptions;
	private ColorPool colorPool;

	private AddFilteredSeriesView addFileteredSeriesView;
	private Map<ExposeType, Series> exposeSeriesMap = new HashMap<ExposeType, Series>();
	private HandlerManager handlerManager;
	private int legendIndex = 0;

	private Column<Expose, String> exposeTableTimeColumn;
	private Column<Expose, String> exposeTableTypeColumn;
	private Column<Expose, String> exposeTableObscuringColumn;
	private Column<Expose, String> exposeTableValueColumn;

	private static class SingletonHolder {
		private static TrafficTimeLineViewImpl instance = new TrafficTimeLineViewImpl();
	}

	public static TrafficTimeLineViewImpl getInstance() {
		return SingletonHolder.instance;
	}

	private static TrafficTimelineViewImplUiBinder uiBinder = GWT
			.create(TrafficTimelineViewImplUiBinder.class);

	interface TrafficTimelineViewImplUiBinder extends
			UiBinder<Widget, TrafficTimeLineViewImpl> {
	}

	private TrafficTimeLineViewImpl() {
		initExposeTable();

		initWidget(uiBinder.createAndBindUi(this));

		handlerManager = new HandlerManager(this);

		final int width = getOptimalTimlineChartWidth();
		final int height = CommonUiUtil.calcHeightFromWidthWithRatio(width, TIMELINE_CHART_ASPECT_RATIO);
		trafficTimelineChart = createTimelineChart("Traffic amount over time",
				"Click and drag in the plot area to zoom in", false, "Time",
				"Traffic amount", width, height);
		trafficTimeLinePlotOptions = createSeriesPlotOptionsForTimeline();
		trafficTimelineChart.setSeriesPlotOptions(trafficTimeLinePlotOptions);
		trafficTimelineChartArea.add(trafficTimelineChart);

		colorPool = new ColorPool();

		// init legend
		initLegend();

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				updateTimelineChartSize();
			}
		});

	}
	
	private void updateTimelineChartSize() {
		final int width = getOptimalTimlineChartWidth();
		final int height = CommonUiUtil.calcHeightFromWidthWithRatio(width, TIMELINE_CHART_ASPECT_RATIO);

		trafficTimelineChart.setSize(width, height);
	}

	private <C> Column<Expose, C> addColumnToExposeTable(final String text,
			final Cell<C> cell, final GetValue<Expose, C> getter,
			final String property) {

		final SortableColumn<Expose, C> column = new SortableColumn<Expose, C>(
				cell, getter, property);

		final TextHeader header = new TextHeader(text);

		column.setSortable(true);
		exposeTable.addColumn(column, header);
		return column;
	}

	private void initExposeTable() {
		exposeTable = new CellTable<Expose>(EXPOSE_TABLE_PAGE_SIZE);
		exposeTable.setWidth("100%", true);
		exposeTable.setEmptyTableWidget(new Span(
				"No expose information available."));
		exposeTable.setLoadingIndicator(new CellTableProgressBar());

		exposeSelectionModel = new NoSelectionModel<Expose>();
		exposeTable.setSelectionModel(exposeSelectionModel);

		exposeTableTimeColumn = addColumnToExposeTable("Time", new TextCell(), // Column<Expose,
																				// String>
				new GetValue<Expose, String>() {
					public String getValue(Expose object) {
						return CommonUiUtil.PRECISE_DATE_TIME_FORMAT
								.format(object.getStartTime());
					}
				}, "time");
		exposeTable.setColumnWidth(exposeTableTimeColumn, 20.0, Unit.PCT);
		// Set as default sort order
		exposeTable.getColumnSortList().push(exposeTableTimeColumn);

		exposeTableTypeColumn = addColumnToExposeTable("Type", new TextCell(),
				new GetValue<Expose, String>() {
					public String getValue(Expose object) {
						return object.getExposeType().getDescription() + " ["
								+ object.getExposeType().getMarker() + "]";
					}
				}, "type");
		exposeTable.setColumnWidth(exposeTableTypeColumn, 15.0, Unit.PCT);

		exposeTableObscuringColumn = addColumnToExposeTable("Obscuring",
				new TextCell(), new GetValue<Expose, String>() {
					public String getValue(Expose object) {
						return object.getExposeObscuring().getDescription();
					}
				}, "obscuring");
		exposeTable.setColumnWidth(exposeTableObscuringColumn, 15.0, Unit.PCT);

		exposeTableValueColumn = addColumnToExposeTable("Value",
				new TextCell(), new GetValue<Expose, String>() {
					public String getValue(Expose object) {
						return object.getExposedData();
					}
				}, "value");
		exposeTable.setColumnWidth(exposeTableValueColumn, 50.0, Unit.PCT);

		exposeTablePager = new FixedSimplePager(
				SimplePager.TextLocation.RIGHT,
				GWT.<SimplePager.Resources> create(SimplePager.Resources.class),
				FAST_FORWARD_BUTTON, FAST_FORWARD_ROWS, LAST_PAGE_BUTTON);
		exposeTablePager.setPageSize(EXPOSE_TABLE_PAGE_SIZE);
		exposeTable.addRangeChangeHandler(new RangeChangeEvent.Handler() {

			@Override
			public void onRangeChange(RangeChangeEvent event) {
				rebuildPager();
			}
		});
		exposeTable.addRowCountChangeHandler(new RowCountChangeEvent.Handler() {

			@Override
			public void onRowCountChange(RowCountChangeEvent event) {
				rebuildPager();
				exposeRowCountInfo.setText(ROW_COUNT_INFO_PREFIX
						+ event.getNewRowCount());
			}
		});

		exposeTablePager.setDisplay(exposeTable);
	}

	private void rebuildPager() {
		exposeTablePagination.clear();

		if (exposeTablePager.getPageCount() == 0) {
			return;
		}

		NavWidget prev = new NavWidget();
		prev.setIcon(IconType.CARET_LEFT);
		prev.setIconSize(IconSize.LARGE);
		prev.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				exposeTablePager.previousPage();
			}
		});
		prev.setDisabled(!exposeTablePager.hasPreviousPage());
		exposeTablePagination.add(prev);

		int before = 2;
		int after = 2;

		while (!exposeTablePager.hasPreviousPages(before) && before > 0) {
			before--;
			if (exposeTablePager.hasNextPages(after + 1)) {
				after++;
			}
		}

		while (!exposeTablePager.hasNextPages(after) && after > 0) {
			after--;
			if (exposeTablePager.hasPreviousPages(before + 1)) {
				before++;
			}
		}

		for (int i = exposeTablePager.getPage() - before; i <= exposeTablePager
				.getPage() + after; i++) {

			final int index = i + 1;

			NavWidget page = new NavWidget();
			page.setText(String.valueOf(index));
			page.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					exposeTablePager.setPage(index - 1);
				}
			});

			if (i == exposeTablePager.getPage()) {
				page.setActive(true);
			}
			exposeTablePagination.add(page);
		}

		NavWidget next = new NavWidget();
		next.setIcon(IconType.CARET_RIGHT);
		next.setIconSize(IconSize.LARGE);
		next.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				exposeTablePager.nextPage();
			}
		});
		next.setDisabled(!exposeTablePager.hasNextPage());
		exposeTablePagination.add(next);
	}

	private int getOptimalTimlineChartWidth() {

		return (int) ((Window.getClientWidth() - 2*CommonUiUtil.FLUID_CONTAINER_PADDING)*CommonUiUtil.FLUID_SPAN_9_ASPECT) - 2 * CommonUiUtil.WELL_PADDING;
	}

	private Chart createTimelineChart(final String title,
			final String subTitle, final boolean withLegend,
			final String xAxisTitle, final String yAxisTitle, final int width, final int height) {

		GWT.log("Creating timeline chart: " + title);

		final Chart timelineChart = new Chart();
		timelineChart.setBackgroundColor("rgb(245,245,245)");
		timelineChart.setPlotBackgroundColor("rgb(245,245,245)");
		timelineChart.setType(Type.AREA);
		timelineChart.setZoomType(ZoomType.X);
		timelineChart.setSize(width, height);

		final Loading loading = new Loading().setStyle(
				new Style().setOption("backgroundColor", "rgb(245,245,245)")
						.setOption("opacity", "0.5")
						.setOption("textAlign", "center"))
				.setLabelStyle(
						new Style().setTop("45%").setColor("black")
								.setFontSize("18px"));
		timelineChart.setLoading(loading);

		final ChartTitle chTitle = new ChartTitle()
				.setText(title)
				.setAlign(ChartTitle.Align.LEFT)
				.setStyle(
						new Style().setFontSize("18px").setColor("#333")
								.setFontFamily("inherit").setFontWeight("bold"));
		final ChartSubtitle chSubTitle = new ChartSubtitle().setText(subTitle)
				.setAlign(ChartTitle.Align.LEFT);
		timelineChart.setTitle(chTitle, chSubTitle);

		final Exporting exporting = new Exporting().setEnabled(true)
				.setFilename(title);
		timelineChart.setExporting(exporting);

		final ToolTip toolTip = new ToolTip().setShared(true)
				.setCrosshairs(true).setBorderColor("#CCCCCC");
		/*
		final ToolTipFormatter formatter = new ToolTipFormatter() {

			@Override
			public String format(ToolTipData toolTipData) {

				GWT.log("start formatter");
				String ret = "";

				if (toolTipData != null) {
					String color;
					String trafficAmount;
					String seriesName;
					ret = ""
							+ CommonUiUtil.PRECISE_DATE_TIME_FORMAT
									.format(new Date(toolTipData.getXAsLong()))
							+ "";

					int nrOfSeries = toolTipData.getPointsLength();

					if (nrOfSeries == 0) {
						Series series = timelineChart.getSeries(toolTipData
								.getSeriesId());

						color = series.getOptions().get("color").isString()
								.stringValue();

						seriesName = toolTipData.getSeriesName();

						trafficAmount = toolTipData.getPoint().getUserData()
								.get("text").isString().stringValue()
								+ "</b>";

						ret += "<br/><span style=\"color:" + color + "\">"
								+ seriesName + "</span>: <b>" + trafficAmount
								+ "</b>";
					} else {
						for (int i = 0; i < nrOfSeries; i++) {
							color = timelineChart
									.getSeries(toolTipData.getSeriesId(i))
									.getOptions().get("color").isString()
									.stringValue();

							trafficAmount = CommonUiUtil
									.humanReadableByteCountGwt(
											toolTipData.getYAsLong(i), true);
							seriesName = toolTipData.getSeriesName(i);
							ret += "<br/><span style=\"color:" + color + "\">"
									+ seriesName + "</span>: <b>"
									+ trafficAmount + "</b>";
						}
					}
				} else {
					GWT.log("tooltip data null");
				}

				return ret;

			}
		};

		toolTip.setFormatter(formatter);
		*/
		timelineChart.setToolTip(toolTip);

		final AreaPlotOptions plotOptions = new AreaPlotOptions();
		plotOptions.setLineWidth(1);
		plotOptions.setHoverStateLineWidth(2);
		plotOptions.setShowInLegend(withLegend);

		Marker marker = new Marker();
		marker.setEnabled(false);
		marker.setHoverState(new Marker().setEnabled(true).setRadius(2));
		plotOptions.setMarker(marker);

		timelineChart.setAreaPlotOptions(plotOptions);

		XAxis xAxis = timelineChart.getXAxis();
		xAxis.setType(Axis.Type.DATE_TIME);
		xAxis.setAxisTitleText(xAxisTitle);

		YAxis yAxis = timelineChart.getYAxis();
		yAxis.setType(Axis.Type.LINEAR);
		yAxis.setAxisTitleText(yAxisTitle);
		yAxis.setShowFirstLabel(true);
		yAxis.setMaxPadding(0.25);
		yAxis.setEndOnTick(false);
		yAxis.setLabels(new YAxisLabels()
				.setFormatter(new AxisLabelsFormatter() {

					@Override
					public String format(AxisLabelsData axisLabelsData) {
						return CommonUiUtil.humanReadableByteCountGwt(
								axisLabelsData.getValueAsLong(), true);
					}
				}));

		GWT.log("Finished creating timeline chart: " + title);
		return timelineChart;
	}

	private SeriesPlotOptions createSeriesPlotOptionsForTimeline() {
		final SeriesPlotOptions seriesOptions = new SeriesPlotOptions();
		seriesOptions.setAllowPointSelect(false);
		seriesOptions.setCursor(Cursor.POINTER);
		seriesOptions.setStickyTracking(false);

		seriesOptions.setPointClickEventHandler(new PointClickEventHandler() {

			@Override
			public boolean onClick(PointClickEvent pointClickEvent) {

				GWT.log("Point click event for: "
						+ pointClickEvent.getSeriesName());

				final Point point = pointClickEvent.getPoint();

				Long httpReqId = new Double(point.getUserData()
						.get("httpReqId").isNumber().doubleValue()).longValue();
				ExposeType exposeType = ExposeType.convertToExposeType(point
						.getUserData().get("exposeType").isString()
						.stringValue());
				ExposeObscuring exposeObscuring = ExposeObscuring
						.convertToExposeObscuring(point.getUserData()
								.get("exposeObscuring").isString()
								.stringValue());
				ExposePoint exposePoint = ExposePoint
						.convertToExposePoint(point.getUserData()
								.get("exposePoint").isString().stringValue());
				String exposedValue = point.getUserData().get("exposedValue")
						.isString().stringValue();

				fireEvent(new SeriesPointClickEvent(httpReqId, exposeType,
						exposeObscuring, exposePoint, exposedValue));

				return true;

			}
		});

		return seriesOptions;
	}

	public String addAreaSeries(List<TimeValuePair> timeValuePairs,
			final String seriesName, int intervalInMs, Long startTime,
			Long endTime, boolean isRemoveAble) {
		GWT.log("Start drawing area series.");

		if (timeValuePairs != null && !timeValuePairs.isEmpty()
				&& startTime != null && endTime != null && intervalInMs > 0) {

			final Series series = trafficTimelineChart.createSeries();
			series.setName(seriesName);
			AreaPlotOptions seriesOptions = new AreaPlotOptions();
			// seriesOptions.setPointInterval(interval);
			seriesOptions.setPointStart(startTime);
			seriesOptions.setColor(colorPool.getNextLineColor());
			seriesOptions.setFillOpacity(0.3);
			seriesOptions.setAllowPointSelect(false);
			series.setPlotOptions(seriesOptions);

			TimeValuePair currentPair = null;
			Iterator<TimeValuePair> timeValuePairIterator = timeValuePairs
					.iterator();
			// get initial pair
			if (timeValuePairIterator.hasNext()) {
				currentPair = timeValuePairIterator.next();
			}

			// loop from startTime increasing by interval to the endTime
			for (Long currentTime = startTime; currentTime < endTime; currentTime = currentTime
					+ intervalInMs) {
				// if currentTime equals current pair time
				if (currentPair != null
						&& currentPair.getTime().getTime() == currentTime) {
					// add value of pair
					series.addPoint(currentTime, currentPair.getValue());
					if (timeValuePairIterator.hasNext()) {
						currentPair = timeValuePairIterator.next();
					}
				} else {
					// add value zero
					series.addPoint(currentTime, 0L);
				}
			}
			trafficTimelineChart.addSeries(series);

			// get id of series
			JSONString idValue = series.getOptions().get("id").isString();
			String seriesId = idValue.stringValue();

			addSeriesToLegend(seriesName, seriesId, seriesOptions.getOptions()
					.get("color").isString().stringValue(), isRemoveAble);

			GWT.log("Finished drawing area series.");
			return seriesId;
		} else {
			showMessage(AlertType.ERROR, "Error",
					"Couldn't draw area series! Empty data or invalid parameters.");
			GWT.log("Couldn't draw area series! Empty data or invalid parameters.");
			return null;
		}

	}

	public void addFlagSeries(List<Expose> exposeList, String onSeriesWithId) {
		GWT.log("Start drawing flag series.");

		if (exposeList != null && !exposeList.isEmpty()
				&& onSeriesWithId != null) {

			for (Expose expose : exposeList) {
				Long time = expose.getStartTime().getTime();
				Long value = expose.getStartTotalTrafficAmount();
				Point p = new Point(time, value);
				p.setOption("x", time);
				ExposeType exposeType = expose.getExposeType();

				p.setOption("title", exposeType.getMarker());

				String text = "\"" + expose.getExposedData() + "\" ("
						+ expose.getExposeObscuring().getDescription()
						+ ") exposed";

				p.setOption("text", text);
				JSONObject userData = new JSONObject();
				userData.put("text", new JSONString(text));
				userData.put("httpReqId", new JSONNumber(expose.getHttpReqId()
						.doubleValue()));
				userData.put("exposeType", new JSONString(expose
						.getExposeType().getDescription()));
				userData.put("exposeObscuring", new JSONString(expose
						.getExposeObscuring().getDescription()));
				userData.put("exposePoint", new JSONString(expose
						.getExposePoint().getDescription()));
				userData.put("exposedValue",
						new JSONString(expose.getExposedData()));
				p.setUserData(userData);
				Series flagSeries = getOrCreateSeries(exposeType,
						exposeType.getDescription(), onSeriesWithId);
				flagSeries.addPoint(p);
			}

			for (Entry<ExposeType, Series> entry : exposeSeriesMap.entrySet()) {
				GWT.log("Add expose: "
						+ entry.getKey().getDescription());
				trafficTimelineChart.addSeries(entry.getValue());
			}

			GWT.log("Finished drawing flag series.");
		} else {
			showMessage(AlertType.INFO, "Info",
					"Couldn't find any epxose data.");
			GWT.log("Couldn't draw flag series! Empty data or invalid parameters.");
		}

	}

	private void addSeriesToLegend(String seriesName, final String seriesId,
			String colorVal, boolean isRemoveAble) {
		final int currentIndex = legendIndex;

		final Label id = new Label(seriesId);
		id.setVisible(false);
		legendTable.setWidget(legendIndex, 0, id);

		final Badge badge = new Badge();
		badge.getElement().getStyle().setProperty("backgroundColor", colorVal);
		legendTable.setWidget(currentIndex, 1, badge);

		final Label name = new Label(seriesName);
		legendTable.setWidget(currentIndex, 2, name);

		final CheckBox active = new CheckBox();
		active.setValue(true);
		// TODO move this handler to activity
		active.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue() == false) {
					trafficTimelineChart.getSeries(seriesId).hide();
				} else {
					trafficTimelineChart.getSeries(seriesId).show();
				}

			}
		});
		legendTable.setWidget(currentIndex, 3, active);

		final Button removeButton = new Button();
		removeButton.setSize(ButtonSize.MINI);
		removeButton.setIcon(IconType.REMOVE);
		if (isRemoveAble) {
			// TODO move this handler to activity
			removeButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					removeSeriesFromLegend(seriesId);
				}
			});
		} else {
			removeButton.setEnabled(false);
		}
		legendTable.setWidget(currentIndex, 4, removeButton);

		legendIndex++;
	}

	private void removeSeriesFromLegend(final String seriesId) {
		final int rows = legendTable.getRowCount();

		for (int i = 0; i < rows; i++) {
			Label idLabel = (Label) legendTable.getWidget(i, 0);
			if (idLabel.getText().equals(seriesId)) {
				GWT.log("Remove area series with id: " + seriesId);
				legendTable.removeRow(i);
				legendIndex--;
				Series series = trafficTimelineChart.getSeries(seriesId);
				if (series != null) {
					trafficTimelineChart.removeSeries(series);
				}
				break;
			}
		}
	}

	private Series getOrCreateSeries(ExposeType exposeType, String seriesName,
			String onSeriesWithId) {
		Series flagSeries = exposeSeriesMap.get(exposeType);
		if (flagSeries == null) {
			flagSeries = trafficTimelineChart.createSeries();

			String colorVal = colorPool.getNextFlagColor();
			flagSeries.setPlotOptions(new SeriesPlotOptions()
					.setColor(colorVal).setShowInLegend(SHOW_DEFAULT_LEGEND));
			flagSeries.setOption("type", "flags");
			flagSeries.setName(seriesName);
			flagSeries.setOption("onSeries", onSeriesWithId);
			flagSeries.setOption("shape", "squarepin");
			exposeSeriesMap.put(exposeType, flagSeries);

			JSONString idValue = flagSeries.getOptions().get("id").isString();
			String seriesId = idValue.stringValue();
			addSeriesToLegend(seriesName, seriesId, colorVal, false);
		}
		return flagSeries;
	}

	private void initLegend() {
		legendIndex = 0;
		legendTable.getRowFormatter().setStyleName(legendIndex,
				"flextable-header");
		legendTable.setWidget(legendIndex, 0, new Label("Series"));
		legendTable.getFlexCellFormatter().setColSpan(legendIndex, 0, 4);
		legendIndex++;
	}

	public void showAddFilteredSeriesView() {

		final Modal dialogBox = new Modal(true);
		dialogBox.setStylePrimaryName("modal large");
		dialogBox.setBackdrop(BackdropType.NORMAL);
		dialogBox.setCloseVisible(true);
		dialogBox.setKeyboard(true);
		dialogBox.setTitle("Add filtered series...");

		final VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSpacing(4);
		dialogBox.add(dialogContents);

		// Add the "add filtered series view"
		addFileteredSeriesView = new AddFilteredSeriesViewImpl();
		dialogContents.add(addFileteredSeriesView.asWidget());

		final Button cancelButton = new Button();
		cancelButton.setText("Cancel");
		cancelButton.setType(ButtonType.DEFAULT);
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		final Button addButton = new Button();
		addButton.setIcon(IconType.PLUS);
		addButton.setText("Add series");
		addButton.setType(ButtonType.PRIMARY);
		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				fireEvent(new AddFilteredSeriesEvent(addFileteredSeriesView
						.getIpCriterias()));
			}
		});

		final ModalFooter footer = new ModalFooter(cancelButton, addButton);
		dialogBox.add(footer);

		dialogBox.show();
	}

	public void showExposeInfoView(ExposeType exposeType,
			ExposeObscuring exposeObscuring, ExposePoint exposePoint,
			String exposedValue, String localIp, String remoteIp,
			String localPort, String remotePort, String remoteCountry,
			String remoteCity, String url, String headerFields,
			String httpParameters) {
		
		final ExposeInfoView exposeInfoView = new ExposeInfoViewImpl();
		exposeInfoView.setData(exposeType, exposeObscuring, exposePoint,
				exposedValue, localIp, remoteIp, localPort, remotePort,
				remoteCountry, remoteCity, url, headerFields, httpParameters);
		
		final ModalInfoBoxView modalBox = ModalInfoBoxViewImpl.getInstance();
		modalBox.init("Data expose", exposeInfoView);
		modalBox.show();

	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		handlerManager.fireEvent(event);
		super.fireEvent(event);
	}

	@Override
	public HandlerRegistration addSeriesPointClickHandler(
			SeriesPointClickEventHandler handler) {
		return handlerManager.addHandler(SeriesPointClickEvent.TYPE, handler);
	}

	public HasClickHandlers getAddFilteredSeriesButton() {
		return addFilteredSeriesButton;
	}

	@Override
	public HandlerRegistration addAddFilteredSeriesHandler(
			final AddFilteredSeriesEventHandler handler) {
		return handlerManager.addHandler(AddFilteredSeriesEvent.TYPE, handler);
	}

	public void showTimelineChartLoadingIndicator() {
		trafficTimelineChart.showLoading(LOADING_MSG);
	}

	public void hideTimelineChartLoadingIndicator() {
		trafficTimelineChart.hideLoading();
	}

	public void clearTimelineChart() {
		exposeSeriesMap = new HashMap<ExposeType, Series>();
		legendIndex = 0;
		// TODO improved clear of legend
		legendTable.removeAllRows();
		initLegend();
		trafficTimelineChart.removeAllSeries();
		colorPool.reset();
	}

	public void goToFirstPageOfExposeTable() {
		exposeTablePager.firstPage();
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

	@Override
	public HasData<Expose> getExposeTable() {
		return exposeTable;
	}

	@Override
	public HandlerRegistration addSelectionChangeHandler(
			SelectionChangeEvent.Handler handler) {
		return exposeSelectionModel.addSelectionChangeHandler(handler);
	}

	@Override
	public HandlerRegistration addColumnSortEventHandlerForExposeTable(
			ColumnSortEvent.ListHandler<Expose> handler) {
		handler.setComparator(exposeTableTimeColumn, new Comparator<Expose>() {
			@Override
			public int compare(Expose o1, Expose o2) {
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null) {
					return (o2 != null) ? o1.getStartTime().compareTo(
							o2.getStartTime()) : 1;
				}
				return -1;
			}
		});
		handler.setComparator(exposeTableTypeColumn, new Comparator<Expose>() {
			@Override
			public int compare(Expose o1, Expose o2) {
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null) {
					return (o2 != null) ? o1.getExposeType().compareTo(
							o2.getExposeType()) : 1;
				}
				return -1;
			}
		});
		handler.setComparator(exposeTableObscuringColumn,
				new Comparator<Expose>() {
					@Override
					public int compare(Expose o1, Expose o2) {
						if (o1 == o2) {
							return 0;
						}
						if (o1 != null) {
							return (o2 != null) ? o1.getExposeObscuring()
									.compareTo(o2.getExposeObscuring()) : 1;
						}
						return -1;
					}
				});
		handler.setComparator(exposeTableValueColumn, new Comparator<Expose>() {
			@Override
			public int compare(Expose o1, Expose o2) {
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null) {
					return (o2 != null) ? o1.getExposedData().compareTo(
							o2.getExposedData()) : 1;
				}
				return -1;
			}
		});
		return exposeTable.addColumnSortHandler(handler);
	}

}
