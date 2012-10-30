package at.tugraz.iaik.nf4droid.shared.constant;

public enum ExposeObscuring {
	PLAIN("plain text"), MD5("MD5 hash"), SHA1("SHA1 hash"), SHA256(
			"SHA256 hash"), OTHER("other");

	private final String description;

	private ExposeObscuring(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static ExposeObscuring convertToExposeObscuring(
			final String description) {
		if (description.equals(PLAIN.getDescription())) {
			return PLAIN;
		} else if (description.equals(MD5.getDescription())) {
			return MD5;
		} else if (description.equals(SHA1.getDescription())) {
			return SHA1;
		} else if (description.equals(SHA256.getDescription())) {
			return SHA256;
		} else {
			return OTHER;
		}
	}
}