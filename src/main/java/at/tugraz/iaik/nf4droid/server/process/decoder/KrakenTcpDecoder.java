package at.tugraz.iaik.nf4droid.server.process.decoder;

import java.util.Collection;

import org.krakenapps.pcap.decoder.ip.Ipv4Packet;
import org.krakenapps.pcap.decoder.ipv6.Ipv6Packet;
import org.krakenapps.pcap.decoder.tcp.TcpDecoder;
import org.krakenapps.pcap.decoder.tcp.TcpPacket;
import org.krakenapps.pcap.decoder.tcp.TcpProtocolMapper;
import org.krakenapps.pcap.decoder.tcp.TcpSegmentCallback;
import org.krakenapps.pcap.decoder.tcp.TcpSegmentCallbacks;
import org.krakenapps.pcap.decoder.tcp.TcpSessionImpl;

public class KrakenTcpDecoder extends TcpDecoder {
	
	private TcpSegmentCallbacks segmentCallbacks;

	@Override
	public TcpProtocolMapper getProtocolMapper() {
		return super.getProtocolMapper();
	}

	@Override
	public void dispatchNewTcpSegment(TcpSessionImpl session, TcpPacket segment) {
		TcpPacket newTcp = new TcpPacket(segment);
		// fix since missing in original library
		segmentCallbacks.fireReceiveCallbacks(session, newTcp);
	}

	@Override
	public Collection<TcpSessionImpl> getCurrentSessions() {
		return super.getCurrentSessions();
	}

	@Override
	public void registerSegmentCallback(TcpSegmentCallback callback) {
		// fix since missing in original library
		segmentCallbacks.register(callback);
	}

	@Override
	public void unregisterSegmentCallback(TcpSegmentCallback callback) {
		// fix since missing in original library
		segmentCallbacks.unregister(callback);
	}

	@Override
	public void process(Ipv4Packet packet) {
		super.process(packet);
	}

	@Override
	public void process(Ipv6Packet packet) {
		super.process(packet);
		
	}

	public KrakenTcpDecoder(TcpProtocolMapper mapper) {
		super(mapper);
		this.segmentCallbacks = new TcpSegmentCallbacks();
	}

}
