package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidProcessingResult;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.ListPath;
import com.mysema.query.types.path.NumberPath;


/**
 * QDroidProcessingResult is a Querydsl query type for DroidProcessingResult
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidProcessingResult extends EntityPathBase<DroidProcessingResult> {

    private static final long serialVersionUID = -740368960;

    public static final QDroidProcessingResult droidProcessingResult = new QDroidProcessingResult("droidProcessingResult");

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket, QDroidArpPacket> arpPackets = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket, QDroidArpPacket>createList("arpPackets", at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket.class, QDroidArpPacket.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DataExpose, QDataExpose> dataExposes = this.<at.tugraz.iaik.nf4droid.server.domain.model.DataExpose, QDataExpose>createList("dataExposes", at.tugraz.iaik.nf4droid.server.domain.model.DataExpose.class, QDataExpose.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame, QDroidEthernetFrame> ethernetFrames = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame, QDroidEthernetFrame>createList("ethernetFrames", at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame.class, QDroidEthernetFrame.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest, QDroidHttpRequest> httpRequests = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest, QDroidHttpRequest>createList("httpRequests", at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest.class, QDroidHttpRequest.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpResponse, QDroidHttpResponse> httpResponses = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpResponse, QDroidHttpResponse>createList("httpResponses", at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpResponse.class, QDroidHttpResponse.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpPacket, QDroidIcmpPacket> icmpPackets = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpPacket, QDroidIcmpPacket>createList("icmpPackets", at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpPacket.class, QDroidIcmpPacket.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpv6Packet, QDroidIcmpv6Packet> icmpv6Packets = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpv6Packet, QDroidIcmpv6Packet>createList("icmpv6Packets", at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpv6Packet.class, QDroidIcmpv6Packet.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv4Packet, QDroidIpv4Packet> ipv4Packets = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv4Packet, QDroidIpv4Packet>createList("ipv4Packets", at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv4Packet.class, QDroidIpv4Packet.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet, QDroidIpv6Packet> ipv6Packets = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet, QDroidIpv6Packet>createList("ipv6Packets", at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet.class, QDroidIpv6Packet.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidPcapPacket, QDroidPcapPacket> pcapPackets = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidPcapPacket, QDroidPcapPacket>createList("pcapPackets", at.tugraz.iaik.nf4droid.server.domain.model.DroidPcapPacket.class, QDroidPcapPacket.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpConnection, QDroidTcpConnection> tcpConnections = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpConnection, QDroidTcpConnection>createList("tcpConnections", at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpConnection.class, QDroidTcpConnection.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpSegment, QDroidTcpSegment> tcpSegments = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpSegment, QDroidTcpSegment>createList("tcpSegments", at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpSegment.class, QDroidTcpSegment.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidUdpPacket, QDroidUdpPacket> udpPackets = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidUdpPacket, QDroidUdpPacket>createList("udpPackets", at.tugraz.iaik.nf4droid.server.domain.model.DroidUdpPacket.class, QDroidUdpPacket.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidProcessingResult(String variable) {
        super(DroidProcessingResult.class, forVariable(variable));
    }

    public QDroidProcessingResult(Path<? extends DroidProcessingResult> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QDroidProcessingResult(PathMetadata<?> metadata) {
        super(DroidProcessingResult.class, metadata);
    }

}

