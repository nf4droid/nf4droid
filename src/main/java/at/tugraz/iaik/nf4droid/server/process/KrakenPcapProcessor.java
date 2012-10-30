package at.tugraz.iaik.nf4droid.server.process;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.krakenapps.pcap.Protocol;
import org.krakenapps.pcap.decoder.ethernet.EthernetType;
import org.krakenapps.pcap.decoder.http.HttpDecoder;
import org.krakenapps.pcap.decoder.icmp.IcmpDecoder;
import org.krakenapps.pcap.decoder.icmpv6.Icmpv6Decoder;
import org.krakenapps.pcap.decoder.ip.InternetProtocol;
import org.krakenapps.pcap.decoder.ip.IpDecoder;
import org.krakenapps.pcap.decoder.ipv6.Ipv6Decoder;
import org.krakenapps.pcap.decoder.tcp.TcpPortProtocolMapper;
import org.krakenapps.pcap.decoder.udp.UdpDecoder;
import org.krakenapps.pcap.decoder.udp.UdpPortProtocolMapper;
import org.krakenapps.pcap.file.PcapFileInputStream;
import org.krakenapps.pcap.packet.PcapPacket;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult;
import at.tugraz.iaik.nf4droid.server.process.decoder.KrakenArpDecoder;
import at.tugraz.iaik.nf4droid.server.process.decoder.KrakenEthernetDecoder;
import at.tugraz.iaik.nf4droid.server.process.decoder.KrakenTcpDecoder;


public class KrakenPcapProcessor {
	
	private static final Logger LOGGER = Logger
			.getLogger(KrakenPcapProcessor.class);
	
	private File pcapFile;

	public KrakenPcapProcessor(File pcapFile) {

		this.pcapFile = pcapFile;
	}
	
	public DroidProcessingResult processPcapFile() throws IOException {
		LOGGER.info("Start processing pcap file.");
		
		Kraken2DomainConverter converter = new Kraken2DomainConverter();
		
		//dummy
		KrakenEthernetDecoder ethDecoder = new KrakenEthernetDecoder();
		
		KrakenEthernetProcessor ethProcessor = new KrakenEthernetProcessor(converter);
		ethDecoder.register(ethProcessor);
		
		
		IpDecoder ipDecoder = new IpDecoder();
		KrakenArpDecoder arpDecoder = new KrakenArpDecoder();
		Ipv6Decoder ipv6Decoder = new Ipv6Decoder();
		ethDecoder.register(EthernetType.IPV4,ipDecoder);
		ethDecoder.register(EthernetType.ARP,arpDecoder);
		ethDecoder.register(EthernetType.IPV6,ipv6Decoder);
		//currently ignoring RARP
		
		KrakenArpProcessor arpProcessor = new KrakenArpProcessor(converter);
		arpDecoder.register(arpProcessor);
		
		/**
		 * covers all ipv4 packets which are not processed with next layer processors
		 */
		KrakenIpProcessor ipProcessor = new KrakenIpProcessor(converter);
		KrakenTcpDecoder tcpDecoder = new KrakenTcpDecoder(new TcpPortProtocolMapper());//keep this decoder to fix bug
		UdpDecoder udpDecoder = new UdpDecoder(new UdpPortProtocolMapper());
		IcmpDecoder icmpDecoder = new IcmpDecoder();
		ipDecoder.register(0,ipProcessor);
		
		ipDecoder.register(InternetProtocol.UDP, udpDecoder);
		ipDecoder.register(InternetProtocol.TCP, tcpDecoder);
		ipDecoder.register(InternetProtocol.ICMP, icmpDecoder);
				
		Icmpv6Decoder icmpv6Decoder = new Icmpv6Decoder();
		/**
		 * covers all ipv6 packets which are not processed with next layer processors
		 */
		KrakenIpv6Processor ipv6Processor = new KrakenIpv6Processor(converter);
		ipv6Decoder.register(0, ipv6Processor);
		ipv6Decoder.register(InternetProtocol.ICMPV6, icmpv6Decoder);
		ipv6Decoder.register(InternetProtocol.TCP, tcpDecoder);
		ipv6Decoder.register(InternetProtocol.UDP, udpDecoder);
		
		HttpDecoder httpDecoder = new HttpDecoder();
		KrakenTcpSegmentCallback tcpSegmentCallback = new KrakenTcpSegmentCallback(converter);
		tcpDecoder.registerSegmentCallback(tcpSegmentCallback);
		tcpDecoder.getProtocolMapper().register(Protocol.HTTP, httpDecoder);
		
		KrakenTcpProcessor tcpProcessor = new KrakenTcpProcessor(converter); //for special things
		tcpDecoder.getProtocolMapper().register(Protocol.HTTP, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.FTP, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.DNS, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.IMAP, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.HTTP, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.MSN, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.MSSQL, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.MYSQL, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.NTP, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.POP3, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.POSTGRES, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.NETBIOS, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.SMB, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.SMTP, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.SNMP, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.SQLNET, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.SSH, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.TELNET, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.TFTP, tcpProcessor);
		tcpDecoder.getProtocolMapper().register(Protocol.WHOIS, tcpProcessor);
		
		KrakenUdpProcessor udpProcessor = new KrakenUdpProcessor(converter);
		//udpDecoder.getProtocolMapper().register(Protocol.DNS, udpProcessor);
		udpDecoder.registerUdpProcessor(udpProcessor);
		
		KrakenIcmpProcessor icmpProcessor = new KrakenIcmpProcessor(converter);
		icmpDecoder.register(icmpProcessor);
		
		KrakenIcmpv6Processor icmpv6Processor = new KrakenIcmpv6Processor(converter);
		icmpv6Decoder.register(icmpv6Processor);

		KrakenHttpProcessor httpProcessor = new KrakenHttpProcessor(converter);
		httpDecoder.register(httpProcessor);

		
		PcapFileInputStream is = null;
		try {
			is = new PcapFileInputStream(pcapFile);
			while (true) {
				PcapPacket packet = is.getPacket();
				if (packet == null)
					break;
				LOGGER.trace("processing pcap packet"+packet.getPacketHeader().toString());
				//processingResult.addPcapPacket(packet);
				ethDecoder.decode(packet);
			}
		} catch (EOFException e) {
			// do nothing
		} finally {
			if (is != null)
				is.close();
		}
	
		LOGGER.info("Finished processing pcap file.");
		return converter.getResult();
	}
	
}
