package at.tugraz.iaik.nf4droid.server.process;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.decoder.icmpv6.Icmpv6Packet;
import org.krakenapps.pcap.decoder.icmpv6.Icmpv6Processor;

public class KrakenIcmpv6Processor implements Icmpv6Processor {

	private Kraken2DomainConverter converter;

	private static final Logger LOGGER = Logger
			.getLogger(KrakenIcmpv6Processor.class);

	public KrakenIcmpv6Processor(Kraken2DomainConverter converter) {
		super();
		this.converter = converter;
	}

	@Override
	public void process(Icmpv6Packet p) {
		LOGGER.trace("processing icmpv6 packet" + p);
		converter.convertAndAddToResult(p);
	}

}
