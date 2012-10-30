

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidHttpHeaderFieldLocator")
public interface DroidHttpHeaderFieldProxy extends EntityProxy {

    abstract Long getId();

    abstract String getFieldName();

    abstract void setFieldName(String fieldName);

    abstract String getFieldValue();

    abstract void setFieldValue(String fieldValue);

    abstract Integer getVersion();
}
