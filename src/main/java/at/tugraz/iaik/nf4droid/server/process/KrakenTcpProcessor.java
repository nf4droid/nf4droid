package at.tugraz.iaik.nf4droid.server.process;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.decoder.tcp.TcpProcessor;
import org.krakenapps.pcap.decoder.tcp.TcpSessionKey;
import org.krakenapps.pcap.util.Buffer;

import at.tugraz.iaik.nf4droid.shared.constant.DroidTcpState;

public class KrakenTcpProcessor implements
		TcpProcessor {
	
	Kraken2DomainConverter converter = new Kraken2DomainConverter();

	private static final Logger LOGGER = Logger
			.getLogger(KrakenTcpProcessor.class);

	public KrakenTcpProcessor(Kraken2DomainConverter converter) {
		super();
		this.converter = converter;
	}

	@Override
	public void handleRx(TcpSessionKey session, Buffer data) {

	}

	@Override
	public void handleTx(TcpSessionKey session, Buffer data) {
	}

	@Override
	public void onEstablish(TcpSessionKey key) {
		LOGGER.trace("tcp onEstablish");
		converter.convertAndAddToResult(key, DroidTcpState.ESTABLISH);
	}

	@Override
	public void onFinish(TcpSessionKey key) {
		LOGGER.trace("tcp onFinish");
		converter.convertAndAddToResult(key, DroidTcpState.FINISH);
	}

	@Override
	public void onReset(TcpSessionKey key) {
		LOGGER.trace("tcp onReset");
		converter.convertAndAddToResult(key, DroidTcpState.RESET);
	}

}
