package at.tugraz.iaik.nf4droid.server.process;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.decoder.ip.InternetProtocol;
import org.krakenapps.pcap.decoder.ip.IpProcessor;
import org.krakenapps.pcap.decoder.ip.Ipv4Packet;

public class KrakenIpProcessor implements IpProcessor {

	private static final Logger LOGGER = Logger
			.getLogger(KrakenIpProcessor.class);

	private Kraken2DomainConverter converter;

	public KrakenIpProcessor(Kraken2DomainConverter converter) {
		super();
		this.converter = converter;
	}

	public void process(Ipv4Packet packet) {
		LOGGER.trace("processing ip packet" + packet);

		final int type = packet.getProtocol();
		if ((type != InternetProtocol.TCP) && (type != InternetProtocol.UDP)
				&& (type != InternetProtocol.IPV6)
				&& (type != InternetProtocol.ICMP)
				&& (type != InternetProtocol.ICMPV6)) {
			converter.convertAndAddToResult(packet);
		}

	}

}
