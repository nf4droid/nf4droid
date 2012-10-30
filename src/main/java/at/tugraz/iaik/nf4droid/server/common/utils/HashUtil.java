package at.tugraz.iaik.nf4droid.server.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.apache.log4j.Logger;

public class HashUtil {

	private enum HashType {
		SHA1("SHA1"), SHA256("SHA-256"), MD5("MD5");
		private final String type;

		private HashType(String t) {
			this.type = t;
		}

		public String getHashType() {
			return type;
		};
	}

	/** Log4j logger object. */
	private static final Logger LOGGER = Logger.getLogger(HashUtil.class);

	private static String getHashValue(final HashType type, final String value) {
		final MessageDigest md;
		try {
			md = MessageDigest.getInstance(type.getHashType());
			final byte[] hashBytes = md.digest(value.getBytes());
			return byteArray2Hex(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Couldn't find algorithm for " + type.getHashType(), e);
			return null;
		}
	}

	public static String getMd5(final String value) {
		return getHashValue(HashType.MD5, value);
	}

	public static String getSha256(final String value) {
		return getHashValue(HashType.SHA256, value);
	}

	public static String getSha1(final String value) {
		return getHashValue(HashType.SHA1, value);
	}

	private static String byteArray2Hex(final byte[] hash) {
		final Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}
}
