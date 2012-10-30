package at.tugraz.iaik.nf4droid.server.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SettingsManager {

	private static final String NF4DROID_PROP_FILE = "nf4droid.properties";

	private static final Logger LOGGER = Logger
			.getLogger(SettingsManager.class);

	private static class SingletonHolder {
		private static SettingsManager instance = new SettingsManager();
	}

	public static SettingsManager getInstance() {
		return SingletonHolder.instance;
	}

	private final String geoIpCityDb;

	private SettingsManager() {
		Properties props = null;
		try {
			props = loadFromMetaInfFolder(NF4DROID_PROP_FILE);
		} catch (final IOException ex) {
			LOGGER.error("Could not find properties file!");
		}

		if (props == null) {
			LOGGER.error("Could not find the properties file \""
					+ NF4DROID_PROP_FILE + "\"!");
			System.exit(1);
		}
		this.geoIpCityDb = props.getProperty("geoip_db");
	}

	public String getGeoIpCityDb() {
		return geoIpCityDb;
	}

	public Properties loadFromMetaInfFolder(final String propertyFile) throws IOException {
		Properties properties = null;
		InputStream is = null;
		
		is = this.getClass().getClassLoader().getResourceAsStream("META-INF/"
				+ propertyFile);
		if (is != null) {
			properties = new Properties();
			properties.load(is);
			is.close();
		}

		return properties;
	}
}
