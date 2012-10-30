package at.tugraz.iaik.nf4droid.client.general.factory;

import at.tugraz.iaik.nf4droid.client.general.AppEntryPointWidget;
import at.tugraz.iaik.nf4droid.client.service.rpc.RpcDashboardDataServiceAsync;
import at.tugraz.iaik.nf4droid.client.service.rpc.RpcGeneralDataServiceAsync;
import at.tugraz.iaik.nf4droid.client.service.rpc.RpcTimelineDataServiceAsync;
import at.tugraz.iaik.nf4droid.shared.requestfactory.AnalyticsRequestFactory;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	AnalyticsRequestFactory getRequestFactory();

	AppEntryPointWidget getEntryPointWidget();

	public RpcDashboardDataServiceAsync getDashboardDataService();
	
	public RpcTimelineDataServiceAsync getTimelineDataService();
	
	public RpcGeneralDataServiceAsync getGeneralDataService();
	
	public PlaceHistoryMapper getHistoryMapper();

}
