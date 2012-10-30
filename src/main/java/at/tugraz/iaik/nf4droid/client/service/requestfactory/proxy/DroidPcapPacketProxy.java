

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidPcapPacket", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidPcapPacketLocator")
public interface DroidPcapPacketProxy extends EntityProxy {

    abstract Long getId();

    abstract Date getArrivalTimeJava();

    abstract void setArrivalTimeJava(Date arrivalTimeJava);

    abstract Long getIncludedLength();

    abstract void setIncludedLength(Long includedLength);

    abstract Long getOriginalLength();

    abstract void setOriginalLength(Long originalLength);

    abstract Integer getVersion();
}
