

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import java.util.List;

import at.tugraz.iaik.nf4droid.shared.constant.DroidHttpMethod;
import at.tugraz.iaik.nf4droid.shared.constant.DroidHttpVersion;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidHttpRequestLocator")
public interface DroidHttpRequestProxy extends EntityProxy {

    abstract Long getId();

    abstract String getUrl();

    abstract void setUrl(String url);

    abstract String getHost();

    abstract void setHost(String host);

    abstract String getQueryString();

    abstract void setQueryString(String queryString);

    abstract String getRemoteIp();

    abstract void setRemoteIp(String remoteIp);

    abstract String getLocalIp();

    abstract void setLocalIp(String localIp);

    abstract Integer getRemotePort();

    abstract void setRemotePort(Integer remotePort);

    abstract Integer getLocalPort();

    abstract void setLocalPort(Integer localPort);

    abstract String getRemoteCountry();

    abstract void setRemoteCountry(String remoteCountry);

    abstract String getRemoteCity();

    abstract void setRemoteCity(String remoteCity);

    abstract Float getRemoteLat();

    abstract void setRemoteLat(Float remoteLat);

    abstract Float getRemoteLon();

    abstract void setRemoteLon(Float remoteLon);

    abstract TrafficCaptureProxy getCapture();

    abstract void setCapture(TrafficCaptureProxy capture);

    abstract DroidHttpVersion getHttpVersion();

    abstract void setHttpVersion(DroidHttpVersion httpVersion);

    abstract DroidHttpMethod getHttpMethod();

    abstract void setHttpMethod(DroidHttpMethod httpMethod);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpHeaderFieldProxy> getHeaderFields();

    abstract void setHeaderFields(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpHeaderFieldProxy> headerFields);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpParameterProxy> getParameters();

    abstract void setParameters(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpParameterProxy> parameters);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DataExposeProxy> getDataExposeList();

    abstract void setDataExposeList(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DataExposeProxy> dataExposeList);

    abstract Integer getVersion();
}
