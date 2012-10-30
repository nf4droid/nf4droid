package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpv6Packet;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;


/**
 * QDroidIcmpv6Packet is a Querydsl query type for DroidIcmpv6Packet
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidIcmpv6Packet extends EntityPathBase<DroidIcmpv6Packet> {

    private static final long serialVersionUID = -1158102251;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidIcmpv6Packet droidIcmpv6Packet = new QDroidIcmpv6Packet("droidIcmpv6Packet");

    public final NumberPath<Integer> checksum = createNumber("checksum", Integer.class);

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Integer> icmpType = createNumber("icmpType", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QDroidIpv6Packet ipv6Packet;

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidIcmpv6Packet(String variable) {
        this(DroidIcmpv6Packet.class, forVariable(variable), INITS);
    }

    public QDroidIcmpv6Packet(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidIcmpv6Packet(PathMetadata<?> metadata, PathInits inits) {
        this(DroidIcmpv6Packet.class, metadata, inits);
    }

    public QDroidIcmpv6Packet(Class<? extends DroidIcmpv6Packet> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ipv6Packet = inits.isInitialized("ipv6Packet") ? new QDroidIpv6Packet(forProperty("ipv6Packet"), inits.get("ipv6Packet")) : null;
    }

}

