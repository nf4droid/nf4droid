package at.tugraz.iaik.nf4droid.server.process;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.decoder.ethernet.EthernetFrame;
import org.krakenapps.pcap.decoder.ethernet.EthernetProcessor;
import org.krakenapps.pcap.decoder.ethernet.EthernetType;

public class KrakenEthernetProcessor implements EthernetProcessor {

	private static final Logger LOGGER = Logger
			.getLogger(KrakenEthernetProcessor.class);

	private Kraken2DomainConverter converter;

	public KrakenEthernetProcessor(Kraken2DomainConverter converter) {
		super();
		this.converter = converter;
	}

	public void process(EthernetFrame frame) {

		LOGGER.trace("processing ethernet frame: " + frame);

		final int type = frame.getType();
		if ((type != EthernetType.IPV4) && (type != EthernetType.IPV6)
				&& (type != EthernetType.ARP)) {
			converter.convertAndAddToResult(frame);
		}
	}

}
