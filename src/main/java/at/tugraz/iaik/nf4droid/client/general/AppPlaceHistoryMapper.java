package at.tugraz.iaik.nf4droid.client.general;

import at.tugraz.iaik.nf4droid.client.place.AddAppPlace;
import at.tugraz.iaik.nf4droid.client.place.AppListPlace;
import at.tugraz.iaik.nf4droid.client.place.AppVersionListPlace;
import at.tugraz.iaik.nf4droid.client.place.CaptureDashboardPlace;
import at.tugraz.iaik.nf4droid.client.place.HttpRequestListPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficCaptureListPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficGeoChartPlace;
import at.tugraz.iaik.nf4droid.client.place.TrafficTimeLinePlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ AppListPlace.Tokenizer.class,
		AppVersionListPlace.Tokenizer.class,
		TrafficCaptureListPlace.Tokenizer.class,
		TrafficGeoChartPlace.Tokenizer.class,
		CaptureDashboardPlace.Tokenizer.class,
		TrafficTimeLinePlace.Tokenizer.class,
		AddAppPlace.Tokenizer.class,
		HttpRequestListPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
