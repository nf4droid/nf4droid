

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv4Packet", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidIpv4PacketLocator")
public interface DroidIpv4PacketProxy extends EntityProxy {

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

    abstract Integer getIpVersion();

    abstract void setIpVersion(Integer ipVersion);

    abstract Integer getHeaderLength();

    abstract void setHeaderLength(Integer headerLength);

    abstract Integer getTypeOfService();

    abstract void setTypeOfService(Integer typeOfService);

    abstract Integer getTotalLength();

    abstract void setTotalLength(Integer totalLength);

    abstract Integer getIdentification();

    abstract void setIdentification(Integer identification);

    abstract Integer getFlags();

    abstract void setFlags(Integer flags);

    abstract Integer getFragmentOffset();

    abstract void setFragmentOffset(Integer fragmentOffset);

    abstract Integer getTimeToLive();

    abstract void setTimeToLive(Integer timeToLive);

    abstract Integer getProtocol();

    abstract void setProtocol(Integer protocol);

    abstract Integer getHeaderChecksum();

    abstract void setHeaderChecksum(Integer headerChecksum);
}
