package at.tugraz.iaik.nf4droid.shared.constant;

public enum ExposeType {

	ANDROID_ID("Android ID", "A"), IMEI("IMEI", "I"), PHONE_NR("Phone number",
			"P"), IMSI("IMSI", "IS"), LOCATION("Location", "L"), USER(
			"User name", "U"), PASSWORD("Password", "P"), SSID("SSID","S"), BSSID("BSSID","BS"), OTHER("other", "O");

	private final String description;
	private final String marker;

	private ExposeType(final String description, final String marker) {
		this.description = description;
		this.marker = marker;
	}

	public String getDescription() {
		return description;
	}

	public String getMarker() {
		return marker;
	}

	public static ExposeType convertToExposeType(final String description) {
		if (description.equals(ANDROID_ID.getDescription())) {
			return ANDROID_ID;
		} else if (description.equals(IMSI.getDescription())) {
			return IMSI;
		} else if (description.equals(IMEI.getDescription())) {
			return IMEI;
		} else if (description.equals(PHONE_NR.getDescription())) {
			return PHONE_NR;
		} else if (description.equals(LOCATION.getDescription())) {
			return LOCATION;
		} else if (description.equals(USER.getDescription())) {
			return USER;
		} else if (description.equals(PASSWORD.getDescription())) {
			return PASSWORD;
		} else if (description.equals(SSID.getDescription())) {
			return SSID;
		} else if (description.equals(BSSID.getDescription())) {
			return BSSID;
		}
		else {
			return OTHER;
		}
	}
}