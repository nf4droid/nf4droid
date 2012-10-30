

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidHttpParameterLocator")
public interface DroidHttpParameterProxy extends EntityProxy {

    abstract Long getId();

    abstract String getParameterName();

    abstract void setParameterName(String parameterName);

    abstract String getParameterValue();

    abstract void setParameterValue(String parameterValue);

    abstract Integer getVersion();
}
