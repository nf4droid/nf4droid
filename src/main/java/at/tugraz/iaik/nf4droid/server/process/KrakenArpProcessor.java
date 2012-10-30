package at.tugraz.iaik.nf4droid.server.process;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.decoder.arp.ArpPacket;
import org.krakenapps.pcap.decoder.arp.ArpProcessor;

public class KrakenArpProcessor implements ArpProcessor {

	private Kraken2DomainConverter converter;

	private static final Logger LOGGER = Logger
			.getLogger(KrakenArpProcessor.class);

	public KrakenArpProcessor(Kraken2DomainConverter converter) {
		super();
		this.converter = converter;
	}

	@Override
	public void process(ArpPacket p) {
		LOGGER.trace("processing arp packet: " + p);
		converter.convertAndAddToResult(p);
	}

}
