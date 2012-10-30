package at.tugraz.iaik.nf4droid.client.place;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class HttpRequestListPlace extends Place {

	private Long trafficCaptureId;

	public HttpRequestListPlace(final Long trafficCaptureId) {
		super();
		this.trafficCaptureId = trafficCaptureId;
	}

	public Long getTrafficCaptureId() {
		return trafficCaptureId;
	}

	@Prefix("httpRequests")
	public static class Tokenizer implements
			PlaceTokenizer<HttpRequestListPlace> {

		@Override
		public HttpRequestListPlace getPlace(String token) {
			final Long trafficCaptureId = CommonUiUtil.safeStringToLong(token);

			if (trafficCaptureId != null) {
				return new HttpRequestListPlace(trafficCaptureId);
			} else {
				return null;
			}

		}

		@Override
		public String getToken(HttpRequestListPlace place) {
			assert place.getTrafficCaptureId() != null;
			return place.getTrafficCaptureId().toString();
		}

	}

}
