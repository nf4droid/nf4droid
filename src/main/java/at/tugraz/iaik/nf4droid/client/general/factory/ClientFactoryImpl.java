package at.tugraz.iaik.nf4droid.client.general.factory;

import at.tugraz.iaik.nf4droid.client.general.AppEntryPointWidget;
import at.tugraz.iaik.nf4droid.client.general.AppPlaceHistoryMapper;
import at.tugraz.iaik.nf4droid.client.service.rpc.RpcDashboardDataService;
import at.tugraz.iaik.nf4droid.client.service.rpc.RpcDashboardDataServiceAsync;
import at.tugraz.iaik.nf4droid.client.service.rpc.RpcGeneralDataService;
import at.tugraz.iaik.nf4droid.client.service.rpc.RpcGeneralDataServiceAsync;
import at.tugraz.iaik.nf4droid.client.service.rpc.RpcTimelineDataService;
import at.tugraz.iaik.nf4droid.client.service.rpc.RpcTimelineDataServiceAsync;
import at.tugraz.iaik.nf4droid.client.view.AnalyticsAppMain;
import at.tugraz.iaik.nf4droid.shared.requestfactory.AnalyticsRequestFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.event.shared.EventBus;

public class ClientFactoryImpl implements ClientFactory {
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			eventBus);
	private final PlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
	private final AnalyticsRequestFactory requestFactory = GWT
			.create(AnalyticsRequestFactory.class);
	private final AppEntryPointWidget entryPointWidget = new AnalyticsAppMain(
			this);
	
	private final RpcDashboardDataServiceAsync visualizationDataService = GWT.create(RpcDashboardDataService.class);
	private final RpcTimelineDataServiceAsync timelineDataService = GWT.create(RpcTimelineDataService.class);
	private final RpcGeneralDataServiceAsync generalDataService = GWT.create(RpcGeneralDataService.class);

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public AnalyticsRequestFactory getRequestFactory() {
		requestFactory.initialize(eventBus);
		return requestFactory;
	}

	@Override
	public AppEntryPointWidget getEntryPointWidget() {
		return entryPointWidget;
	}

	public RpcDashboardDataServiceAsync getDashboardDataService() {
		return visualizationDataService;
	}

	@Override
	public RpcTimelineDataServiceAsync getTimelineDataService() {
		return timelineDataService;
	}

	@Override
	public RpcGeneralDataServiceAsync getGeneralDataService() {
		return generalDataService;
	}

	@Override
	public PlaceHistoryMapper getHistoryMapper() {
		return historyMapper;
	}
}
