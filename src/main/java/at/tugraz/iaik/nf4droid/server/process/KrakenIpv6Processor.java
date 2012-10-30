package at.tugraz.iaik.nf4droid.server.process;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.decoder.ip.InternetProtocol;
import org.krakenapps.pcap.decoder.ipv6.Ipv6Packet;
import org.krakenapps.pcap.decoder.ipv6.Ipv6Processor;

public class KrakenIpv6Processor implements Ipv6Processor {

	private static final Logger LOGGER = Logger
			.getLogger(KrakenIpv6Processor.class);

	private Kraken2DomainConverter converter;

	public KrakenIpv6Processor(Kraken2DomainConverter converter) {
		super();
		this.converter = converter;
	}

	@Override
	public void process(Ipv6Packet packet) {
		LOGGER.trace("processing ipv6 packet");
		final int type = packet.getNextHeader();
		if ((type != InternetProtocol.TCP) && (type != InternetProtocol.UDP)
				&& (type != InternetProtocol.ICMPV6)) {
			converter.convertAndAddToResult(packet);
		}
	}

}
