package at.tugraz.iaik.nf4droid.client.view.common;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

public class CommonUiUtil {

	public static final DateTimeFormat DEFAULT_DATE_TIME_FORMAT = DateTimeFormat
			.getFormat("yyyy-MM-dd HH:mm");

	public static final DateTimeFormat PRECISE_DATE_TIME_FORMAT = DateTimeFormat
			.getFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static String humanReadableByteCountGwt(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1)
				+ (si ? "" : "i");
		return NumberFormat.getFormat("#.#")
				.format(bytes / Math.pow(unit, exp)) + " " + pre + "B";
	}

	public static Long safeStringToLong(final String token) {
		if (token != null && !token.isEmpty()) {
			try {
				return Long.parseLong(token);
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Integer safeStringToInt(final String token) {
		if (token != null && !token.isEmpty()) {
			try {
				return Integer.parseInt(token);
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(l
					+ " cannot be cast to int without changing its value.");
		}
		return (int) l;
	}

	public static String limitStringLength(final String value,
			final int maxLength) {
		if (value != null && value.length() > maxLength) {
			return value.substring(0, maxLength - 4).concat("...");
		}
		return value;
	}
	
	public static String insertLineWrapsIfLongString(final String value, final int maxLineLength) {
		if (value != null && value.length() > maxLineLength) {
			return value.substring(0, maxLineLength)+"<br/>"+insertLineWrapsIfLongString(value.substring(maxLineLength, value.length()), maxLineLength);
		} else {
			return value;
		}
	}
	public static int calcHeightFromWidthWithRatio(final int width,
			final double aspectRatio) {
		return (int) Math.round(width / aspectRatio);
	}

	public static final int WELL_PADDING = 20;

	public static final int FLUID_CONTAINER_PADDING = 20;

	public static final double FLUID_SPAN_9_ASPECT = 0.744;

	public static final double FLUID_SPAN_4_ASPECT = 0.957;

	public static final int ERROR_MESSAGE_HIDE_DELAY = 15000;
}
