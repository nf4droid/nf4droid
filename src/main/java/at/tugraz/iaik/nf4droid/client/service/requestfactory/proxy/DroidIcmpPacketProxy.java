

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpPacket", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidIcmpPacketLocator")
public interface DroidIcmpPacketProxy extends EntityProxy {

    abstract Long getId();

    abstract DroidIpv4PacketProxy getIpv4Packet();

    abstract void setIpv4Packet(DroidIpv4PacketProxy ipv4Packet);

    abstract Integer getIcmpType();

    abstract void setIcmpType(Integer icmpType);

    abstract Integer getCode();

    abstract void setCode(Integer code);

    abstract Integer getChecksum();

    abstract void setChecksum(Integer checksum);

    abstract Integer getIcmpId();

    abstract void setIcmpId(Integer icmpId);

    abstract Integer getSeq();

    abstract void setSeq(Integer seq);

    abstract Integer getVersion();
}
