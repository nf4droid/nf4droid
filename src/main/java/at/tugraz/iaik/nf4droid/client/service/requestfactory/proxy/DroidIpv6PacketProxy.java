

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidIpv6PacketLocator")
public interface DroidIpv6PacketProxy extends EntityProxy {

    abstract Long getId();

    abstract DroidEthernetFrameProxy getEthernetFrame();

    abstract void setEthernetFrame(DroidEthernetFrameProxy ethernetFrame);

    abstract String getSourceIp();

    abstract void setSourceIp(String sourceIp);

    abstract Boolean getSourceIsLocal();

    abstract void setSourceIsLocal(Boolean sourceIsLocal);

    abstract Boolean getDestIsLocal();

    abstract void setDestIsLocal(Boolean destIsLocal);

    abstract String getDestIp();

    abstract void setDestIp(String destIp);

    abstract String getRemoteCountry();

    abstract void setRemoteCountry(String remoteCountry);

    abstract String getRemoteCity();

    abstract void setRemoteCity(String remoteCity);

    abstract Float getRemoteLat();

    abstract void setRemoteLat(Float remoteLat);

    abstract Float getRemoteLon();

    abstract void setRemoteLon(Float remoteLon);

    abstract Integer getVersion();

    abstract Integer getFlowLabel();

    abstract void setFlowLabel(Integer flowLabel);

    abstract Integer getPayloadLength();

    abstract void setPayloadLength(Integer payloadLength);

    abstract Integer getHopLimit();

    abstract void setHopLimit(Integer hopLimit);

    abstract Byte getTrafficClass();

    abstract void setTrafficClass(Byte trafficClass);

    abstract Byte getNextHeader();

    abstract void setNextHeader(Byte nextHeader);
}
