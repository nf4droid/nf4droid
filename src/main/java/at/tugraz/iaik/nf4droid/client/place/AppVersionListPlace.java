package at.tugraz.iaik.nf4droid.client.place;

import at.tugraz.iaik.nf4droid.client.view.common.CommonUiUtil;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class AppVersionListPlace extends Place {

	private Long appId;

	public AppVersionListPlace(Long appId) {
		this.appId = appId;
	}

	public Long getAppId() {
		return appId;
	}

	@Prefix("app")
	public static class Tokenizer implements
			PlaceTokenizer<AppVersionListPlace> {

		@Override
		public AppVersionListPlace getPlace(String token) {
			Long appId = CommonUiUtil.safeStringToLong(token);
			if (appId != null) {
				return new AppVersionListPlace(appId);
			} else {
				return null;
			}
		}

		@Override
		public String getToken(AppVersionListPlace place) {
			assert place.getAppId() != null;
			return place.getAppId().toString();
		}

	}

}
