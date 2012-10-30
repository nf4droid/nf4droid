

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring;
import at.tugraz.iaik.nf4droid.shared.constant.ExposePoint;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DataExpose", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DataExposeLocator")
public interface DataExposeProxy extends EntityProxy {

    abstract Long getId();

    abstract Long getStartTotalTrafficAmount();

    abstract void setStartTotalTrafficAmount(Long startTotalTrafficAmount);

    abstract ExposeType getExposeType();

    abstract void setExposeType(ExposeType exposeType);

    abstract ExposeObscuring getExposeObscuring();

    abstract void setExposeObscuring(ExposeObscuring exposeObscuring);

    abstract ExposePoint getExposePoint();

    abstract void setExposePoint(ExposePoint exposePoint);

    abstract String getExposedData();

    abstract void setExposedData(String exposedData);

    abstract DroidHttpRequestProxy getHttpRequest();

    abstract void setHttpRequest(DroidHttpRequestProxy httpRequest);

    abstract Integer getVersion();
}
