

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import at.tugraz.iaik.nf4droid.shared.constant.DroidTcpState;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpConnection", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidTcpConnectionLocator")
public interface DroidTcpConnectionProxy extends EntityProxy {

    abstract Long getId();

    abstract String getClientIp();

    abstract void setClientIp(String clientIp);

    abstract String getServerIp();

    abstract void setServerIp(String serverIp);

    abstract Integer getClientPort();

    abstract void setClientPort(Integer clientPort);

    abstract Integer getServerPort();

    abstract void setServerPort(Integer serverPort);

    abstract String getServerDomain();

    abstract void setServerDomain(String serverDomain);

    abstract String getServerCountry();

    abstract void setServerCountry(String serverCountry);

    abstract String getServerCity();

    abstract void setServerCity(String serverCity);

    abstract Float getServerLatitude();

    abstract void setServerLatitude(Float serverLatitude);

    abstract Float getServerLongitude();

    abstract void setServerLongitude(Float serverLongitude);

    abstract DroidTcpState getState();

    abstract void setState(DroidTcpState state);

    abstract Integer getVersion();
}
