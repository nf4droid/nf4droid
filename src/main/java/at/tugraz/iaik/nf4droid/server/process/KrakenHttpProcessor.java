package at.tugraz.iaik.nf4droid.server.process;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.decoder.http.HttpProcessor;
import org.krakenapps.pcap.decoder.http.HttpRequest;
import org.krakenapps.pcap.decoder.http.HttpResponse;
import org.krakenapps.pcap.util.Buffer;

public class KrakenHttpProcessor implements HttpProcessor {

	private Kraken2DomainConverter converter;
	private static final Logger LOGGER = Logger
			.getLogger(KrakenHttpProcessor.class);

	public KrakenHttpProcessor(Kraken2DomainConverter converter) {
		super();
		this.converter = converter;
	}

	public void onRequest(HttpRequest req) {
		LOGGER.trace("processing http request");
		converter.convertAndAddToResult(req);
	}

	public void onResponse(HttpRequest req, HttpResponse resp) {
		// TODO add http request
		LOGGER.trace("processing http response");
		converter.convertAndAddToResult(resp);
	}

	public void onMultipartData(Buffer buffer) {

		LOGGER.trace("processing http multipart");
	}

}
