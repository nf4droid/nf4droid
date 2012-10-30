package at.tugraz.iaik.nf4droid.shared.constant;

public enum ExposePoint {

	HTTP_PARAMETER("Http parameter"), HEADER_FIELD("Header field"), OTHER(
			"other");

	private final String description;

	private ExposePoint(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static ExposePoint convertToExposePoint(final String description) {
		if (description.equals(HTTP_PARAMETER.getDescription())) {
			return HTTP_PARAMETER;
		} else if (description.equals(HEADER_FIELD.getDescription())) {
			return HEADER_FIELD;
		} else {
			return OTHER;
		}
	}

}