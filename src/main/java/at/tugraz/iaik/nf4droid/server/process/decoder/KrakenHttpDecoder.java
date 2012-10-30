package at.tugraz.iaik.nf4droid.server.process.decoder;

import org.krakenapps.pcap.decoder.http.HttpDecoder;
import org.krakenapps.pcap.decoder.http.HttpProcessor;
import org.krakenapps.pcap.decoder.tcp.TcpSessionKey;
import org.krakenapps.pcap.util.Buffer;

public class KrakenHttpDecoder extends HttpDecoder {
	
	@Override
	public void register(HttpProcessor processor) {
		super.register(processor);
	}

	@Override
	public void unregister(HttpProcessor processor) {
		super.unregister(processor);
	}

	@Override
	public void onFinish(TcpSessionKey session) {
		super.onFinish(session);
		
	}

	@Override
	public void onReset(TcpSessionKey session) {
		super.onReset(session);
		
	}

	@Override
	public void dispatchMultipartData(byte[] data, int offset, int length) {
		super.dispatchMultipartData(data, offset, length);
	}

	@Override
	public void handleRx(TcpSessionKey sessionKey, Buffer data) {
		super.handleRx(sessionKey, data);
	}

	@Override
	public void handleTx(TcpSessionKey sessionKey, Buffer data) {
		super.handleTx(sessionKey, data);
	}

	@Override
	public void onEstablish(TcpSessionKey session) {
		super.onEstablish(session);
		
		
	}

}
