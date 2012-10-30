

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpv6Packet", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidIcmpv6PacketLocator")
public interface DroidIcmpv6PacketProxy extends EntityProxy {

    abstract Long getId();

    abstract DroidIpv6PacketProxy getIpv6Packet();

    abstract void setIpv6Packet(DroidIpv6PacketProxy ipv6Packet);

    abstract Integer getIcmpType();

    abstract void setIcmpType(Integer icmpType);

    abstract Integer getCode();

    abstract void setCode(Integer code);

    abstract Integer getChecksum();

    abstract void setChecksum(Integer checksum);

    abstract Integer getVersion();
}
