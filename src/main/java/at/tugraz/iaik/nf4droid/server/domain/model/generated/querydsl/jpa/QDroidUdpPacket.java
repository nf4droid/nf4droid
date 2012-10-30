package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidUdpPacket;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;


/**
 * QDroidUdpPacket is a Querydsl query type for DroidUdpPacket
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidUdpPacket extends EntityPathBase<DroidUdpPacket> {

    private static final long serialVersionUID = -368215815;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidUdpPacket droidUdpPacket = new QDroidUdpPacket("droidUdpPacket");

    public final NumberPath<Integer> checksum = createNumber("checksum", Integer.class);

    public final NumberPath<Integer> destPort = createNumber("destPort", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QDroidIpv4Packet ipv4Packet;

    public final QDroidIpv6Packet ipv6Packet;

    public final NumberPath<Integer> length = createNumber("length", Integer.class);

    public final NumberPath<Integer> sourcePort = createNumber("sourcePort", Integer.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidUdpPacket(String variable) {
        this(DroidUdpPacket.class, forVariable(variable), INITS);
    }

    public QDroidUdpPacket(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidUdpPacket(PathMetadata<?> metadata, PathInits inits) {
        this(DroidUdpPacket.class, metadata, inits);
    }

    public QDroidUdpPacket(Class<? extends DroidUdpPacket> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ipv4Packet = inits.isInitialized("ipv4Packet") ? new QDroidIpv4Packet(forProperty("ipv4Packet"), inits.get("ipv4Packet")) : null;
        this.ipv6Packet = inits.isInitialized("ipv6Packet") ? new QDroidIpv6Packet(forProperty("ipv6Packet"), inits.get("ipv6Packet")) : null;
    }

}

