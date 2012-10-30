

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.AppVersion", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.AppVersionLocator")
public interface AppVersionProxy extends EntityProxy {

    abstract Long getId();

    abstract AppProxy getApp();

    abstract void setApp(AppProxy app);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.TrafficCaptureProxy> getTrafficCaptures();

    abstract void setTrafficCaptures(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.TrafficCaptureProxy> trafficCaptures);

    abstract String getVersionName();

    abstract void setVersionName(String versionName);

    abstract Integer getVersionCode();

    abstract void setVersionCode(Integer versionCode);

    abstract Integer getVersion();
}
