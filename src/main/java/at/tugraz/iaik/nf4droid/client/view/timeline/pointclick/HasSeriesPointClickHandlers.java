package at.tugraz.iaik.nf4droid.client.view.timeline.pointclick;

import com.google.gwt.event.shared.HandlerRegistration;

public interface HasSeriesPointClickHandlers {

	public HandlerRegistration addSeriesPointClickHandler(final SeriesPointClickEventHandler handler);
	
}
