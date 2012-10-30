package at.tugraz.iaik.nf4droid.server.process.decoder;

import org.krakenapps.pcap.decoder.ethernet.EthernetDecoder;
import org.krakenapps.pcap.decoder.ethernet.EthernetProcessor;
import org.krakenapps.pcap.packet.PcapPacket;

public class KrakenEthernetDecoder extends EthernetDecoder {

	@Override
	public void register(EthernetProcessor processor) {
		super.register(processor);
	}

	@Override
	public void register(int type, EthernetProcessor processor) {
		super.register(type, processor);
	}

	@Override
	public void unregister(EthernetProcessor processor) {
		super.unregister(processor);
	}

	@Override
	public void unregister(int type, EthernetProcessor processor) {
		super.unregister(type, processor);
	}

	@Override
	public void decode(PcapPacket packet) {
		super.decode(packet);

	}

}
