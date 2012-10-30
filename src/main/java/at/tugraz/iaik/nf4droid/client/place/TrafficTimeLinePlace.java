package at.tugraz.iaik.nf4droid.client.place;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class TrafficTimeLinePlace extends Place {

	private Long trafficCaptureId;

	public TrafficTimeLinePlace(final Long trafficCaptureId) {
		super();
		this.trafficCaptureId = trafficCaptureId;
	}

	public Long getTrafficCaptureId() {
		return trafficCaptureId;
	}

	@Prefix("trafficTimeline")
	public static class Tokenizer implements
			PlaceTokenizer<TrafficTimeLinePlace> {

		@Override
		public TrafficTimeLinePlace getPlace(String token) {
			final Long trafficCaptureId = CommonUiUtil.safeStringToLong(token);

			if (trafficCaptureId != null) {
				return new TrafficTimeLinePlace(trafficCaptureId);
			} else {
				return null;
			}

		}

		@Override
		public String getToken(TrafficTimeLinePlace place) {
			assert place.getTrafficCaptureId() != null;
			return place.getTrafficCaptureId().toString();
		}

	}

}
