package at.tugraz.iaik.nf4droid.client.place;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class CaptureDashboardPlace extends Place {

	private Long trafficCaptureId;

	public CaptureDashboardPlace(final Long trafficCaptureId) {
		super();
		this.trafficCaptureId = trafficCaptureId;
	}

	public Long getTrafficCaptureId() {
		return trafficCaptureId;
	}

	@Prefix("trafficCapture")
	public static class Tokenizer implements
			PlaceTokenizer<CaptureDashboardPlace> {

		@Override
		public CaptureDashboardPlace getPlace(String token) {
			final Long trafficCaptureId = CommonUiUtil.safeStringToLong(token);

			if (trafficCaptureId != null) {
				return new CaptureDashboardPlace(trafficCaptureId);
			} else {
				return null;
			}

		}

		@Override
		public String getToken(CaptureDashboardPlace place) {
			assert place.getTrafficCaptureId() != null;
			return place.getTrafficCaptureId().toString();
		}

	}

}
