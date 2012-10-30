

package at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyForName;

@ProxyForName(value = "at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult", locator = "at.tugraz.iaik.nf4droid.server.service.requestfactory.locator.DroidProcessingResultLocator")
public interface DroidProcessingResultProxy extends EntityProxy {

    abstract Long getId();

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidTcpConnectionProxy> getTcpConnections();

    abstract void setTcpConnections(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidTcpConnectionProxy> tcpConnections);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpRequestProxy> getHttpRequests();

    abstract void setHttpRequests(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpRequestProxy> httpRequests);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpResponseProxy> getHttpResponses();

    abstract void setHttpResponses(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidHttpResponseProxy> httpResponses);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidArpPacketProxy> getArpPackets();

    abstract void setArpPackets(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidArpPacketProxy> arpPackets);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidEthernetFrameProxy> getEthernetFrames();

    abstract void setEthernetFrames(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidEthernetFrameProxy> ethernetFrames);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIcmpPacketProxy> getIcmpPackets();

    abstract void setIcmpPackets(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIcmpPacketProxy> icmpPackets);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIcmpv6PacketProxy> getIcmpv6Packets();

    abstract void setIcmpv6Packets(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIcmpv6PacketProxy> icmpv6Packets);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIpv4PacketProxy> getIpv4Packets();

    abstract void setIpv4Packets(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIpv4PacketProxy> ipv4Packets);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIpv6PacketProxy> getIpv6Packets();

    abstract void setIpv6Packets(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidIpv6PacketProxy> ipv6Packets);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidPcapPacketProxy> getPcapPackets();

    abstract void setPcapPackets(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidPcapPacketProxy> pcapPackets);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidTcpSegmentProxy> getTcpSegments();

    abstract void setTcpSegments(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidTcpSegmentProxy> tcpSegments);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidUdpPacketProxy> getUdpPackets();

    abstract void setUdpPackets(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DroidUdpPacketProxy> udpPackets);

    abstract List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DataExposeProxy> getDataExposes();

    abstract void setDataExposes(List<at.tugraz.iaik.nf4droid.client.service.requestfactory.proxy.DataExposeProxy> dataExposes);

    abstract Integer getVersion();
}
