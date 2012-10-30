package at.tugraz.iaik.nf4droid.client.view.timeline.addseries;

import com.google.gwt.event.shared.GwtEvent;

public class AddFilteredSeriesEvent extends
		GwtEvent<AddFilteredSeriesEventHandler> {

	

	public static final Type<AddFilteredSeriesEventHandler> TYPE = new Type<AddFilteredSeriesEventHandler>();
		
	private UiCriteria ipCriterias;
	
	
	public AddFilteredSeriesEvent(UiCriteria ipCriterias) {
		super();
		this.ipCriterias = ipCriterias;
	}
	
	@Override
	public GwtEvent.Type<AddFilteredSeriesEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddFilteredSeriesEventHandler handler) {
		handler.onAddFilteredSeries(this);
	}

	/**
	 * @return the ipCriterias
	 */
	public UiCriteria getIpCriterias() {
		return ipCriterias;
	}

	
}
