package at.tugraz.iaik.nf4droid.server.process.decoder;

import org.krakenapps.pcap.decoder.ip.Ipv4Packet;
import org.krakenapps.pcap.decoder.ipv6.Ipv6Packet;
import org.krakenapps.pcap.decoder.udp.UdpDecoder;
import org.krakenapps.pcap.decoder.udp.UdpProcessor;
import org.krakenapps.pcap.decoder.udp.UdpProtocolMapper;

public class KrakenUdpDecoder extends UdpDecoder {

	public KrakenUdpDecoder(UdpProtocolMapper protocolMapper) {
		super(protocolMapper);
	}

	@Override
	public UdpProtocolMapper getProtocolMapper() {
		return super.getProtocolMapper();
	}

	@Override
	public void setProtocolMapper(UdpProtocolMapper protocolMapper) {
		super.setProtocolMapper(protocolMapper);
	}

	@Override
	public void registerUdpProcessor(UdpProcessor processor) {
		super.registerUdpProcessor(processor);
	}

	@Override
	public void unregisterUdpProcessor(UdpProcessor processor) {
		super.unregisterUdpProcessor(processor);
	}

	@Override
	public void process(Ipv4Packet packet) {
		super.process(packet);
	}

	@Override
	public void process(Ipv6Packet p) {
		super.process(p);
	}

}
