package at.tugraz.iaik.nf4droid.server.process;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.krakenapps.pcap.decoder.arp.ArpPacket;
import org.krakenapps.pcap.decoder.ethernet.EthernetFrame;
import org.krakenapps.pcap.decoder.ethernet.EthernetType;
import org.krakenapps.pcap.decoder.http.HttpMethod;
import org.krakenapps.pcap.decoder.http.HttpRequest;
import org.krakenapps.pcap.decoder.http.HttpResponse;
import org.krakenapps.pcap.decoder.http.HttpVersion;
import org.krakenapps.pcap.decoder.http.impl.HttpRequestImpl;
import org.krakenapps.pcap.decoder.icmp.IcmpPacket;
import org.krakenapps.pcap.decoder.icmpv6.Icmpv6Packet;
import org.krakenapps.pcap.decoder.ip.IpPacket;
import org.krakenapps.pcap.decoder.ip.Ipv4Packet;
import org.krakenapps.pcap.decoder.ipv6.Ipv6Packet;
import org.krakenapps.pcap.decoder.tcp.TcpDirection;
import org.krakenapps.pcap.decoder.tcp.TcpSegment;
import org.krakenapps.pcap.decoder.tcp.TcpSessionKey;
import org.krakenapps.pcap.decoder.udp.UdpPacket;
import org.krakenapps.pcap.packet.PacketHeader;
import org.krakenapps.pcap.packet.PcapPacket;

import at.tugraz.iaik.nf4droid.server.common.utils.GeoIpUtil;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidAbstractIpPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpResponse;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpv6Packet;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv4Packet;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidPcapPacket;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpConnection;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpSegment;
import at.tugraz.iaik.nf4droid.server.domain.model.DroidUdpPacket;
import at.tugraz.iaik.nf4droid.shared.constant.DroidEthernetType;
import at.tugraz.iaik.nf4droid.shared.constant.DroidHttpMethod;
import at.tugraz.iaik.nf4droid.shared.constant.DroidHttpVersion;
import at.tugraz.iaik.nf4droid.shared.constant.DroidTcpDirection;
import at.tugraz.iaik.nf4droid.shared.constant.DroidTcpState;

import com.maxmind.geoip.Location;

public class Kraken2DomainConverter {

	private DroidProcessingResult result;

	public Kraken2DomainConverter() {
		super();
		this.result = new DroidProcessingResult();
	}

	public DroidProcessingResult getResult() {
		return result;
	}

	public DroidPcapPacket convertAndAddToResult(PcapPacket pcapPacket) {
		DroidPcapPacket packet = new DroidPcapPacket();
		setDataAndAddToResult(packet, pcapPacket);
		return packet;
	}

	private void setDataAndAddToResult(DroidPcapPacket packet,
			PcapPacket pcapPacket) {
		PacketHeader header = pcapPacket.getPacketHeader();
		packet.setIncludedLength(Long.valueOf(header.getInclLen()));
		packet.setOriginalLength(Long.valueOf(header.getOrigLen()));

		long timeInMs = ((long) header.getTsSec() * 1000)
				+ ((long) header.getTsUsec() / 1000);

		Instant instant = new Instant(timeInMs);
		packet.setArrivalTimeInstant(instant);

		DateTime time = new DateTime(timeInMs);
		packet.setArrivalTime(time);

		result.addPcapPacket(packet);
	}

	public DroidEthernetFrame convertAndAddToResult(EthernetFrame ethFrame) {
		DroidEthernetFrame frame = new DroidEthernetFrame();
		setDataAndAddToResult(frame, ethFrame);
		return frame;
	}

	private void setDataAndAddToResult(DroidEthernetFrame frame,
			EthernetFrame ethFrame) {
		frame.setSourceMac(ethFrame.getSource().toString());
		frame.setDestMac(ethFrame.getDestination().toString());
		frame.setType(convertKrakenEthernetType(ethFrame.getType()));

		setDataAndAddToResult(frame.getPcapPacket(), ethFrame.getPcapPacket());

		result.addEthernetFrame(frame);
	}

	public DroidArpPacket convertAndAddToResult(ArpPacket arpPacket) {
		DroidArpPacket packet = new DroidArpPacket();
		setDataAndAddToResult(packet, arpPacket);
		return packet;
	}

	private void setDataAndAddToResult(DroidArpPacket packet,
			ArpPacket arpPacket) {
		packet.setSenderMac(arpPacket.getSenderMac().toString());
		packet.setSenderIp(arpPacket.getSenderIp().getHostAddress().toString());
		packet.setTargetMac(arpPacket.getTargetMac().toString());
		packet.setTargetIp(arpPacket.getTargetIp().getHostAddress().toString());
		packet.setHardwareSize(arpPacket.getHardwareSize());
		packet.setHardwareType(arpPacket.getHardwareType());
		packet.setProtocolType(arpPacket.getProtocolType());
		packet.setProtocolSize(arpPacket.getProtocolSize());
		packet.setOpcode(arpPacket.getOpcode());

		setDataAndAddToResult(packet.getEthernetFrame(),
				(EthernetFrame) arpPacket.getL2Frame());

		result.addArpPacket(packet);
	}

	public DroidIpv4Packet convertAndAddToResult(Ipv4Packet ipv4Packet) {
		DroidIpv4Packet packet = new DroidIpv4Packet();
		setDataAndAddToResult(packet, ipv4Packet);
		return packet;
	}

	private void setDataAndAddToResult(DroidIpv4Packet packet,
			Ipv4Packet ipv4Packet) {
		packet.setIpVersion(ipv4Packet.getVersion());
		packet.setHeaderLength(ipv4Packet.getIhl());
		packet.setTypeOfService(ipv4Packet.getTos());
		packet.setTotalLength(ipv4Packet.getTotalLength());
		packet.setIdentification(ipv4Packet.getId());
		packet.setFlags(ipv4Packet.getFlags());
		packet.setFragmentOffset(ipv4Packet.getFragmentOffset());
		packet.setTimeToLive(ipv4Packet.getTtl());
		packet.setProtocol(ipv4Packet.getProtocol());
		packet.setHeaderChecksum(ipv4Packet.getHeaderChecksum());

		// fix incorrect naming according to direction
		packet.setSourceIp(ipv4Packet.getDestinationAddress().getHostAddress());
		packet.setDestIp(ipv4Packet.getSourceAddress().getHostAddress());

		setLocationInfos(packet, ipv4Packet.getSourceAddress(),
				ipv4Packet.getDestinationAddress());

		setDataAndAddToResult(packet.getEthernetFrame(),
				(EthernetFrame) ipv4Packet.getL2Frame());

		result.addIpv4Packet(packet);
	}

	private void setLocationInfos(DroidAbstractIpPacket ipPacket,
			InetAddress sourceAddress, InetAddress destAddress) {

		Location location = null;
		if (!sourceAddress.isSiteLocalAddress()) {
			location = GeoIpUtil.getInstance().getIPLocation(sourceAddress);
			ipPacket.setSourceIsLocal(false);
			ipPacket.setDestIsLocal(true);
		} else if (!destAddress.isSiteLocalAddress()) {
			location = GeoIpUtil.getInstance().getIPLocation(destAddress);
			ipPacket.setSourceIsLocal(true);
			ipPacket.setDestIsLocal(false);
		} else {
			ipPacket.setSourceIsLocal(true);
			ipPacket.setDestIsLocal(true);
		}

		if (location != null) {
			ipPacket.setRemoteCountry(location.countryName);
			ipPacket.setRemoteCity(location.city);
			ipPacket.setRemoteLat(location.latitude);
			ipPacket.setRemoteLon(location.longitude);
		}

	}

	public DroidIpv6Packet convertAndAddToResult(Ipv6Packet ipv6Packet) {
		DroidIpv6Packet packet = new DroidIpv6Packet();
		setDataAndAddToResult(packet, ipv6Packet);
		return packet;
	}

	private void setDataAndAddToResult(DroidIpv6Packet packet,
			Ipv6Packet ipv6Packet) {
		packet.setFlowLabel(ipv6Packet.getFlowLabel());
		packet.setPayloadLength(ipv6Packet.getPayloadLength());
		packet.setHopLimit(ipv6Packet.getHopLimit());
		packet.setTrafficClass(ipv6Packet.getTrafficClass());
		packet.setNextHeader(ipv6Packet.getNextHeader());
		packet.setSourceIp(ipv6Packet.getSourceAddress().getHostAddress());
		packet.setDestIp(ipv6Packet.getDestinationAddress().getHostAddress());

		setLocationInfos(packet, ipv6Packet.getSourceAddress(),
				ipv6Packet.getDestinationAddress());

		setDataAndAddToResult(packet.getEthernetFrame(),
				(EthernetFrame) ipv6Packet.getL2Frame());

		result.addIpv6Packet(packet);
	}

	public DroidTcpSegment convertAndAddToResult(TcpSegment tcpSegment) {
		DroidTcpSegment segment = null;
		IpPacket ipPacket = tcpSegment.getIpPacket();
		if (ipPacket instanceof Ipv4Packet) {
			segment = new DroidTcpSegment(DroidTcpSegment.IpVersion.IPV4);
			setDataAndAddToResult(segment, tcpSegment,
					DroidTcpSegment.IpVersion.IPV4);
		} else if (ipPacket instanceof Ipv6Packet) {
			segment = new DroidTcpSegment(DroidTcpSegment.IpVersion.IPV6);
			setDataAndAddToResult(segment, tcpSegment,
					DroidTcpSegment.IpVersion.IPV6);
		} else {
			// XXX throw exception
		}

		return segment;
	}

	private void setDataAndAddToResult(DroidTcpSegment segment,
			TcpSegment tcpSegment, DroidTcpSegment.IpVersion ipVersion) {
		// fix incorrect naming according to direction
		segment.setSourcePort(tcpSegment.getDestinationPort());
		segment.setDestPort(tcpSegment.getSourcePort());
		segment.setAck(tcpSegment.isAck());
		segment.setFin(tcpSegment.isFin());
		segment.setPsh(tcpSegment.isPsh());
		segment.setRst(tcpSegment.isRst());
		segment.setUrg(tcpSegment.isUrg());
		segment.setSyn(tcpSegment.isSyn());
		segment.setAckNr(tcpSegment.getAck());
		segment.setSeqNr(tcpSegment.getSeq());
		segment.setRelativeAckNr(tcpSegment.getRelativeAck());
		segment.setRelativeSeqNr(tcpSegment.getRelativeSeq());
		segment.setTcpLength(tcpSegment.getTotalLength());
		segment.setDirection(convertKrakenTcpDirection(tcpSegment
				.getDirection()));

		if (ipVersion == DroidTcpSegment.IpVersion.IPV4) {
			setDataAndAddToResult(segment.getIpv4Packet(),
					(Ipv4Packet) tcpSegment.getIpPacket());
		} else {
			setDataAndAddToResult(segment.getIpv6Packet(),
					(Ipv6Packet) tcpSegment.getIpPacket());
		}
		result.addTcpSegment(segment);

	}

	public DroidUdpPacket convertAndAddToResult(UdpPacket udpPacket) {
		DroidUdpPacket packet = new DroidUdpPacket();
		IpPacket ipPacket = udpPacket.getIpPacket();
		if (ipPacket instanceof Ipv4Packet) {
			packet = new DroidUdpPacket(DroidUdpPacket.IpVersion.IPV4);
			setDataAndAddToResult(packet, udpPacket,
					DroidUdpPacket.IpVersion.IPV4);
		} else if (ipPacket instanceof Ipv6Packet) {
			packet = new DroidUdpPacket(DroidUdpPacket.IpVersion.IPV6);
			setDataAndAddToResult(packet, udpPacket,
					DroidUdpPacket.IpVersion.IPV6);
		} else {
			// XXX throw exception
		}
		return packet;
	}

	private void setDataAndAddToResult(DroidUdpPacket packet,
			UdpPacket udpPacket, DroidUdpPacket.IpVersion ipVersion) {
		packet.setLength(udpPacket.getLength());
		packet.setChecksum(udpPacket.getChecksum());
		packet.setSourcePort(udpPacket.getSourcePort());
		packet.setDestPort(udpPacket.getDestinationPort());

		if (ipVersion == DroidUdpPacket.IpVersion.IPV4) {
			setDataAndAddToResult(packet.getIpv4Packet(),
					(Ipv4Packet) udpPacket.getIpPacket());
		} else {
			setDataAndAddToResult(packet.getIpv6Packet(),
					(Ipv6Packet) udpPacket.getIpPacket());
		}

		result.addUdpPacket(packet);
	}

	public DroidIcmpPacket convertAndAddToResult(IcmpPacket icmpPacket) {
		DroidIcmpPacket packet = new DroidIcmpPacket();
		setDataAndAddToResult(packet, icmpPacket);
		return packet;
	}

	private void setDataAndAddToResult(DroidIcmpPacket packet,
			IcmpPacket icmpPacket) {
		packet.setSeq(icmpPacket.getSeq());
		packet.setChecksum(icmpPacket.getChecksum());
		packet.setCode(icmpPacket.getCode());
		packet.setIcmpId(icmpPacket.getId());
		packet.setIcmpType(icmpPacket.getType());

		setDataAndAddToResult(packet.getIpv4Packet(),
				(Ipv4Packet) icmpPacket.getIpPacket());

		result.addIcmpPacket(packet);
	}

	public DroidIcmpv6Packet convertAndAddToResult(Icmpv6Packet icmpPacket) {
		DroidIcmpv6Packet packet = new DroidIcmpv6Packet();
		setDataAndAddToResult(packet, icmpPacket);
		return packet;
	}

	private void setDataAndAddToResult(DroidIcmpv6Packet packet,
			Icmpv6Packet icmpPacket) {
		packet.setChecksum(icmpPacket.getChecksum());
		packet.setCode(icmpPacket.getCode());
		packet.setIcmpType(icmpPacket.getType());

		setDataAndAddToResult(packet.getIpv6Packet(),
				(Ipv6Packet) icmpPacket.getIpPacket());

		result.addIcmpv6Packet(packet);
	}

	public DroidHttpRequest convertAndAddToResult(HttpRequest httpRequest) {
		DroidHttpRequest request = new DroidHttpRequest();

		try {
			final URL url = httpRequest.getURL();
			
			if (url != null) {
				request.setUrl(url.toString());
				request.setHost(url.getHost());
			}
		} catch (Exception e) {
			//TODO prober error handling
			System.out.println("Error: invalid Http request url! " + e);
			return null;
		}
		request.setQueryString(httpRequest.getQueryString());

		// fix incorrect naming of remote and local
		InetSocketAddress tmpAddr = httpRequest.getRemoteAddress();
		request.setLocalIp(tmpAddr.getAddress().getHostAddress());
		request.setLocalPort(tmpAddr.getPort());

		// fix incorrect naming of remote and local
		tmpAddr = httpRequest.getLocalAddress();
		request.setRemoteIp(tmpAddr.getAddress().getHostAddress());
		request.setRemotePort(tmpAddr.getPort());

		Location location = GeoIpUtil.getInstance().getIPLocation(
				tmpAddr.getAddress());
		if (location != null) {
			request.setRemoteCountry(location.countryName);
			request.setRemoteCity(location.city);
			request.setRemoteLat(location.latitude);
			request.setRemoteLon(location.longitude);
		}

		if (httpRequest.getHttpVersion() != null) {
			request.setHttpVersion(convertHttpVersion(httpRequest
					.getHttpVersion()));
		} else {
			//TODO prober error handling
			System.out.println("Error: missing HTTP version!");
			return null;
		}
		if (httpRequest.getMethod() != null) {
			request.setHttpMethod(convertHttpMethod(httpRequest.getMethod()));
		} else {
			//TODO prober error handling
			System.out.println("Error: missing HTTP method!");
			return null;
		}

		List<DroidHttpHeaderField> headerFields = new ArrayList<DroidHttpHeaderField>();
		for (String fieldName : httpRequest.getHeaderKeys()) {
			headerFields.add(convertHttpHeaderField(fieldName,
					httpRequest.getHeader(fieldName)));
		}
		request.setHeaderFields(headerFields);

		if (httpRequest instanceof HttpRequestImpl) {
			HttpRequestImpl httpReqImpl = (HttpRequestImpl) httpRequest;

			List<DroidHttpParameter> parameters = new ArrayList<DroidHttpParameter>();
			for (String paramName : httpReqImpl.getParameterKeys()) {
				if (paramName != " ") {
					parameters.add(convertHttpParameter(paramName,
							httpReqImpl.getParameter(paramName)));
				}
			}
			request.setParameters(parameters);
		}

		result.addHttpRequest(request);
		return request;
	}

	public DroidHttpResponse convertAndAddToResult(HttpResponse httpResponse) {
		DroidHttpResponse response = new DroidHttpResponse();

		response.setStatusCode(httpResponse.getStatusCode());
		response.setStatusLine(httpResponse.getStatusLine());

		response.setHttpVersion(convertHttpVersion(httpResponse
				.getHttpVersion()));

		List<DroidHttpHeaderField> headerFields = new ArrayList<DroidHttpHeaderField>();
		for (String fieldName : httpResponse.getHeaderKeys()) {
			headerFields.add(convertHttpHeaderField(fieldName,
					httpResponse.getHeader(fieldName)));
		}
		response.setHeaderFields(headerFields);

		response.setContent(httpResponse.getContent());

		result.addHttpResponse(response);
		return response;
	}

	public DroidTcpConnection convertAndAddToResult(TcpSessionKey sessionKey,
			DroidTcpState state) {
		DroidTcpConnection con = new DroidTcpConnection();

		con.setClientIp(sessionKey.getClientIp().getHostAddress().toString());
		con.setClientPort(sessionKey.getClientPort());
		con.setServerIp(sessionKey.getServerIp().getHostAddress().toString());
		con.setServerPort(sessionKey.getServerPort());
		con.setState(state);
		Location location = GeoIpUtil.getInstance().getIPLocation(
				sessionKey.getServerIp());
		if (location != null) {
			con.setServerCountry(location.countryName);
			con.setServerCity(location.city);
			con.setServerLatitude(location.latitude);
			con.setServerLongitude(location.longitude);
		}

		result.addTcpConnection(con);
		return con;
	}

	private DroidHttpHeaderField convertHttpHeaderField(String fieldName,
			String fieldValue) {
		DroidHttpHeaderField field = new DroidHttpHeaderField();
		field.setFieldName(fieldName);
		field.setFieldValue(fieldValue);
		return field;
	}

	private DroidHttpParameter convertHttpParameter(String paramName,
			String paramValue) {
		DroidHttpParameter param = new DroidHttpParameter();
		param.setParameterName(paramName);
		param.setParameterValue(paramValue);
		return param;
	}

	private DroidHttpMethod convertHttpMethod(HttpMethod method) {
		switch (method) {
		case GET:
			return DroidHttpMethod.GET;
		case POST:
			return DroidHttpMethod.POST;
		case CONNECT:
			return DroidHttpMethod.CONNECT;
		case HEAD:
			return DroidHttpMethod.HEAD;
		case DELETE:
			return DroidHttpMethod.DELETE;
		case PUT:
			return DroidHttpMethod.PUT;
		case OPTIONS:
			return DroidHttpMethod.OPTIONS;
		case TRACE:
			return DroidHttpMethod.TRACE;
		default:
			// XXX throw exception
			return null;
		}
	}

	private DroidHttpVersion convertHttpVersion(HttpVersion method) {
		switch (method) {
		case HTTP_1_0:
			return DroidHttpVersion.HTTP_1_0;
		case HTTP_1_1:
			return DroidHttpVersion.HTTP_1_1;
		default:
			// XXX throw exception
			return null;
		}
	}

	private DroidTcpDirection convertKrakenTcpDirection(TcpDirection direction) {
		if (direction != null) {
			switch (direction) {
			case ToServer:
				return DroidTcpDirection.ToServer;
			case ToClient:
				return DroidTcpDirection.ToClient;
			default:
				// XXX throw exception
				break;
			}
		}
		return null;
	}

	private DroidEthernetType convertKrakenEthernetType(int ethernetType) {
		switch (ethernetType) {
		case EthernetType.IPV4:
			return DroidEthernetType.IPV4;
		case EthernetType.IPV6:
			return DroidEthernetType.IPV6;
		case EthernetType.ARP:
			return DroidEthernetType.ARP;
		case EthernetType.RARP:
			return DroidEthernetType.RARP;
		default:
			// XXX throw exception
			return null;
		}
	}
}
