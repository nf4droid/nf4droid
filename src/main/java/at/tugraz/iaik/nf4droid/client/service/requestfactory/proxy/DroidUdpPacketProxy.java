

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidUdpPacket", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidUdpPacketLocator")
public interface DroidUdpPacketProxy extends EntityProxy {

    abstract Long getId();

    abstract DroidIpv4PacketProxy getIpv4Packet();

    abstract void setIpv4Packet(DroidIpv4PacketProxy ipv4Packet);

    abstract DroidIpv6PacketProxy getIpv6Packet();

    abstract void setIpv6Packet(DroidIpv6PacketProxy ipv6Packet);

    abstract Integer getLength();

    abstract void setLength(Integer length);

    abstract Integer getChecksum();

    abstract void setChecksum(Integer checksum);

    abstract Integer getDestPort();

    abstract void setDestPort(Integer destPort);

    abstract Integer getSourcePort();

    abstract void setSourcePort(Integer sourcePort);

    abstract Integer getVersion();
}
