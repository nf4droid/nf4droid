package at.tugraz.iaik.nf4droid.client.view.timeline;

import java.util.List;

import at.tugraz.iaik.nf4droid.client.view.timeline.addseries.HasAddFilteredSeriesHandlers;
import at.tugraz.iaik.nf4droid.client.view.timeline.pointclick.HasSeriesPointClickHandlers;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring;
import at.tugraz.iaik.nf4droid.shared.constant.ExposePoint;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;
import at.tugraz.iaik.nf4droid.shared.dto.Expose;
import at.tugraz.iaik.nf4droid.shared.dto.TimeValuePair;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent.HasSelectionChangedHandlers;

public interface TrafficTimeLineView extends IsWidget, HasSeriesPointClickHandlers, HasAddFilteredSeriesHandlers, HasSelectionChangedHandlers {

	public HasData<Expose> getExposeTable();
	
	public String addAreaSeries(List<TimeValuePair> timeValuePairs,
			final String seriesName, int intervalInMs, Long startTime,
			Long endTime, boolean isRemoveAble);
	
	public void addFlagSeries(List<Expose> exposeList, String onSeriesWithId);
	
	
	public void showTimelineChartLoadingIndicator();
	
	public void hideTimelineChartLoadingIndicator();
	
	public void clearTimelineChart();
	
	public void showMessage(AlertType alertType, final String heading, final String message);
	public void hideMessage();
	
	public HasClickHandlers getAddFilteredSeriesButton();
	
	public void showAddFilteredSeriesView();
	
	public void showExposeInfoView(ExposeType exposeType,
			ExposeObscuring exposeObscuring, ExposePoint exposePoint,
			String exposedValue, String localIp, String remoteIp,
			String localPort, String remotePort, String remoteCountry,
			String remoteCity, String url, String headerFields,
			String httpParameters);
	
	public void goToFirstPageOfExposeTable();
	
	public HandlerRegistration addColumnSortEventHandlerForExposeTable(
			ColumnSortEvent.ListHandler<Expose> handler);
}
