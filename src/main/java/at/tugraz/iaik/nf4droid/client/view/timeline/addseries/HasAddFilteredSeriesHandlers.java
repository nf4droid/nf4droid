package at.tugraz.iaik.nf4droid.client.view.timeline.addseries;

import com.google.gwt.event.shared.HandlerRegistration;

public interface HasAddFilteredSeriesHandlers {

	public HandlerRegistration addAddFilteredSeriesHandler(final AddFilteredSeriesEventHandler handler);
	
}
