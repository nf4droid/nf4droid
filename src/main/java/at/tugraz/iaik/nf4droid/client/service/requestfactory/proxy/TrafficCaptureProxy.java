

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.TrafficCaptureLocator")
public interface TrafficCaptureProxy extends EntityProxy {

    abstract Long getId();

    abstract DroidProcessingResultProxy getProcessingResult();

    abstract void setProcessingResult(DroidProcessingResultProxy processingResult);

    abstract AppVersionProxy getAppVersion();

    abstract void setAppVersion(AppVersionProxy appVersion);

    abstract Date getDateAdded();

    abstract void setDateAdded(Date dateAdded);

    abstract String getDescription();

    abstract void setDescription(String description);

    abstract String getPhoneNr();

    abstract void setPhoneNr(String phoneNr);

    abstract String getAndroidId();

    abstract void setAndroidId(String androidId);

    abstract Long getImsi();

    abstract void setImsi(Long imsi);

    abstract Long getImei();

    abstract void setImei(Long imei);

    abstract String getUser();

    abstract void setUser(String user);

    abstract String getPassword();

    abstract void setPassword(String password);

    abstract String getSsid();

    abstract void setSsid(String ssid);

    abstract String getBssid();

    abstract void setBssid(String bssid);

    abstract String getLatitude();

    abstract void setLatitude(String latitude);

    abstract Integer getVersion();
}
