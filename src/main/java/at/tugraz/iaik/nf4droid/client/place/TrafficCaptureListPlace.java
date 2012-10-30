package at.tugraz.iaik.nf4droid.client.place;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class TrafficCaptureListPlace extends Place {

	private Long appVersionId;

	public TrafficCaptureListPlace(final Long appVersionId) {
		this.appVersionId = appVersionId;
	}

	public Long getAppVersionId() {
		return appVersionId;
	}

	@Prefix("appVersion")
	public static class Tokenizer implements
			PlaceTokenizer<TrafficCaptureListPlace> {

		@Override
		public TrafficCaptureListPlace getPlace(String token) {
			final Long appVersionId = CommonUiUtil.safeStringToLong(token);

			if (appVersionId != null) {
				return new TrafficCaptureListPlace(appVersionId);
			} else {
				return null;
			}

		}

		@Override
		public String getToken(TrafficCaptureListPlace place) {
			assert place.getAppVersionId() != null;
			return place.getAppVersionId().toString();
		}

	}

}
