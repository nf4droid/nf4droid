package at.tugraz.iaik.nf4droid.server.process;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.decoder.icmp.IcmpPacket;
import org.krakenapps.pcap.decoder.icmp.IcmpProcessor;

public class KrakenIcmpProcessor implements IcmpProcessor {

	private Kraken2DomainConverter converter;

	private static final Logger LOGGER = Logger
			.getLogger(KrakenIcmpProcessor.class);

	public KrakenIcmpProcessor(Kraken2DomainConverter converter) {
		super();
		this.converter = converter;
	}

	@Override
	public void process(IcmpPacket p) {
		LOGGER.trace("processing icmp packet" + p);
		converter.convertAndAddToResult(p);
	}

}
