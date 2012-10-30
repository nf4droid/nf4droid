package at.tugraz.iaik.nf4droid.client.place;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class TrafficGeoChartPlace extends Place {

	private Long trafficCaptureId;

	public TrafficGeoChartPlace(final Long trafficCaptureId) {
		super();
		this.trafficCaptureId = trafficCaptureId;
	}

	public Long getTrafficCaptureId() {
		return trafficCaptureId;
	}

	@Prefix("trafficGeoChart")
	public static class Tokenizer implements
			PlaceTokenizer<TrafficGeoChartPlace> {

		@Override
		public TrafficGeoChartPlace getPlace(String token) {
			final Long trafficCaptureId = CommonUiUtil.safeStringToLong(token);

			if (trafficCaptureId != null) {
				return new TrafficGeoChartPlace(trafficCaptureId);
			} else {
				return null;
			}

		}

		@Override
		public String getToken(TrafficGeoChartPlace place) {
			assert place.getTrafficCaptureId() != null;
			return place.getTrafficCaptureId().toString();
		}

	}

}
