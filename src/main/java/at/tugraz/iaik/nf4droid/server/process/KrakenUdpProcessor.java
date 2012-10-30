package at.tugraz.iaik.nf4droid.server.process;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.decoder.udp.UdpPacket;
import org.krakenapps.pcap.decoder.udp.UdpProcessor;

public class KrakenUdpProcessor implements UdpProcessor {

	private Kraken2DomainConverter converter;

	private static final Logger LOGGER = Logger
			.getLogger(KrakenUdpProcessor.class);

	public KrakenUdpProcessor(Kraken2DomainConverter converter) {
		super();
		this.converter = converter;
	}

	@Override
	public void process(UdpPacket p) {
		LOGGER.trace("processing upd packet: " + p);
		converter.convertAndAddToResult(p);

		// TODO take care of ip packet underneath updpacket

	}

}
