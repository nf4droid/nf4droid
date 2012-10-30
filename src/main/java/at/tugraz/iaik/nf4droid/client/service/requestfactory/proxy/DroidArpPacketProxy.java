

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidArpPacketLocator")
public interface DroidArpPacketProxy extends EntityProxy {

    abstract Long getId();

    abstract DroidEthernetFrameProxy getEthernetFrame();

    abstract void setEthernetFrame(DroidEthernetFrameProxy ethernetFrame);

    abstract String getSenderMac();

    abstract void setSenderMac(String senderMac);

    abstract String getSenderIp();

    abstract void setSenderIp(String senderIp);

    abstract String getTargetMac();

    abstract void setTargetMac(String targetMac);

    abstract String getTargetIp();

    abstract void setTargetIp(String targetIp);

    abstract Integer getHardwareType();

    abstract void setHardwareType(Integer hardwareType);

    abstract Integer getProtocolType();

    abstract void setProtocolType(Integer protocolType);

    abstract Integer getHardwareSize();

    abstract void setHardwareSize(Integer hardwareSize);

    abstract Integer getProtocolSize();

    abstract void setProtocolSize(Integer protocolSize);

    abstract Integer getOpcode();

    abstract void setOpcode(Integer opcode);

    abstract Integer getVersion();
}
