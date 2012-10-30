package at.tugraz.iaik.nf4droid.server.process.decoder;

import org.krakenapps.pcap.decoder.ethernet.EthernetFrame;
import org.krakenapps.pcap.decoder.ip.IpDecoder;
import org.krakenapps.pcap.decoder.ip.IpProcessor;

public class KrakenIpDecoder extends IpDecoder {

	@Override
	public void register(int protocol, IpProcessor processor) {
		super.register(protocol, processor);
	}

	@Override
	public void unregister(int protocol, IpProcessor processor) {
		super.unregister(protocol, processor);
	}

	@Override
	public void process(EthernetFrame frame) {
		super.process(frame);
		
	}

}
