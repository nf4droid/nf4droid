

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.constant.DroidHttpVersion;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpResponse", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidHttpResponseLocator")
public interface DroidHttpResponseProxy extends EntityProxy {

    abstract Long getId();

    abstract TrafficCaptureProxy getCapture();

    abstract void setCapture(TrafficCaptureProxy capture);

    abstract String getStatusLine();

    abstract void setStatusLine(String statusLine);

    abstract DroidHttpVersion getHttpVersion();

    abstract void setHttpVersion(DroidHttpVersion httpVersion);

    abstract Integer getStatusCode();

    abstract void setStatusCode(Integer statusCode);

    abstract String getContent();

    abstract void setContent(String content);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpHeaderFieldProxy> getHeaderFields();

    abstract void setHeaderFields(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpHeaderFieldProxy> headerFields);

    abstract Integer getVersion();
}
