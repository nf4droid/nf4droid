

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.App", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.AppLocator")
public interface AppProxy extends EntityProxy {

    abstract Long getId();

    abstract String getAppName();

    abstract void setAppName(String appName);

    abstract String getAppPackage();

    abstract void setAppPackage(String appPackage);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.AppVersionProxy> getAppVersions();

    abstract void setAppVersions(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.AppVersionProxy> appVersions);

    abstract Integer getVersion();
}
