package at.tugraz.iaik.nf4droid.client;

import at.tugraz.iaik.nf4droid.client.general.AppEntryPointWidget;
import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactory;
import at.tugraz.iaik.nf4droid.client.general.factory.ClientFactoryImpl;
import at.tugraz.iaik.nf4droid.client.place.AppListPlace;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

public class NF4Droid implements EntryPoint {
	private ClientFactory clientFactory = GWT.create(ClientFactoryImpl.class);
	private Place defaultPlace = new AppListPlace();

	
	@Override
	public void onModuleLoad() {
		
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();
		AppEntryPointWidget entryPointWidget = clientFactory
				.getEntryPointWidget();
		Widget widget = entryPointWidget.getAppWidget();

		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				clientFactory.getHistoryMapper());
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootLayoutPanel.get().add(widget);
		
		// Goes to place represented on URL or default place
		historyHandler.handleCurrentHistory();
				
		// Remove loading information
		DOM.removeChild(RootPanel.getBodyElement(),
				DOM.getElementById("loadingBox"));

	}

}
