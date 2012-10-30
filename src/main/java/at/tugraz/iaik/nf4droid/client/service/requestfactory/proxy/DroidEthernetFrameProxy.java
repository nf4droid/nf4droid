

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import at.tugraz.iaik.nf4droid.shared.constant.DroidEthernetType;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidEthernetFrameLocator")
public interface DroidEthernetFrameProxy extends EntityProxy {

    abstract Long getId();

    abstract DroidPcapPacketProxy getPcapPacket();

    abstract void setPcapPacket(DroidPcapPacketProxy pcapPacket);

    abstract String getSourceMac();

    abstract void setSourceMac(String sourceMac);

    abstract String getDestMac();

    abstract void setDestMac(String destMac);

    abstract DroidEthernetType getType();

    abstract void setType(DroidEthernetType type);

    abstract Integer getVersion();
}
