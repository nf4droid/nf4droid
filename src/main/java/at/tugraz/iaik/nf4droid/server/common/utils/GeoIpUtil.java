package at.tugraz.iaik.nf4droid.server.common.utils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;

import org.apache.log4j.Logger;

import at.tugraz.iaik.nf4droid.server.common.SettingsManager;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class GeoIpUtil {

	/** Log4j logger object. */
	private static final Logger LOGGER = Logger
			.getLogger(GeoIpUtil.class);

	private LookupService service;

	private GeoIpUtil() {
		try {
			URL url = this.getClass().getClassLoader().getResource(SettingsManager.getInstance().getGeoIpCityDb());
			
			File geoIpCityLiteDb = new File(url.getPath());
			LOGGER.info("Using GeoIpCityLiteDB: "+geoIpCityLiteDb.getAbsolutePath());
			
			service = new LookupService(geoIpCityLiteDb);
		} catch (IOException e) {
			LOGGER.error("Couldn't start GeoIP location service", e);
		}
	}

	private static class SingletonHolder {
		private static GeoIpUtil instance = new GeoIpUtil();
	}

	public static GeoIpUtil getInstance() {
		return SingletonHolder.instance;
	}

	public Location getIPLocation(InetAddress address) {
		Location location = null;

		if ((service != null) && (address != null)) {
			location = service.getLocation(address);
			//LOGGER.info("Retrieve location for ip address "
			//		+ (address.getHostAddress() == null ? "null" : address.getHostAddress()) + " -> "+location.countryName+" "+location.city);
		}

		return location;
	}

}
