package at.tugraz.iaik.nf4droid.server.process;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.decoder.tcp.TcpSegment;
import org.krakenapps.pcap.decoder.tcp.TcpSegmentCallback;
import org.krakenapps.pcap.decoder.tcp.TcpSession;

public class KrakenTcpSegmentCallback implements TcpSegmentCallback {

	private static final Logger LOGGER = Logger
			.getLogger(KrakenTcpSegmentCallback.class);

	private Kraken2DomainConverter converter;

	public KrakenTcpSegmentCallback(Kraken2DomainConverter converter) {
		super();
		this.converter = converter;
	}

	public void onReceive(TcpSession session, TcpSegment segment) {

		LOGGER.trace("processing tcp segment callback" + segment);
		converter.convertAndAddToResult(segment);

	}

}
