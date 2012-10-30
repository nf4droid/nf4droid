

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import at.tugraz.iaik.nf4droid.shared.constant.DroidTcpDirection;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpSegment", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidTcpSegmentLocator")
public interface DroidTcpSegmentProxy extends EntityProxy {

    abstract Long getId();

    abstract DroidIpv4PacketProxy getIpv4Packet();

    abstract void setIpv4Packet(DroidIpv4PacketProxy ipv4Packet);

    abstract DroidIpv6PacketProxy getIpv6Packet();

    abstract void setIpv6Packet(DroidIpv6PacketProxy ipv6Packet);

    abstract Integer getDestPort();

    abstract void setDestPort(Integer destPort);

    abstract Integer getSourcePort();

    abstract void setSourcePort(Integer sourcePort);

    abstract Integer getSeqNr();

    abstract void setSeqNr(Integer seqNr);

    abstract Integer getAckNr();

    abstract void setAckNr(Integer ackNr);

    abstract Integer getRelativeSeqNr();

    abstract void setRelativeSeqNr(Integer relativeSeqNr);

    abstract Integer getRelativeAckNr();

    abstract void setRelativeAckNr(Integer relativeAckNr);

    abstract Integer getTcpLength();

    abstract void setTcpLength(Integer tcpLength);

    abstract DroidTcpDirection getDirection();

    abstract void setDirection(DroidTcpDirection direction);

    abstract Integer getVersion();
}
