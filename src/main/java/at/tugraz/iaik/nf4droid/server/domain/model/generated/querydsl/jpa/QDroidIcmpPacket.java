package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidIcmpPacket;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;


/**
 * QDroidIcmpPacket is a Querydsl query type for DroidIcmpPacket
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidIcmpPacket extends EntityPathBase<DroidIcmpPacket> {

    private static final long serialVersionUID = -1746031659;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidIcmpPacket droidIcmpPacket = new QDroidIcmpPacket("droidIcmpPacket");

    public final NumberPath<Integer> checksum = createNumber("checksum", Integer.class);

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Integer> icmpId = createNumber("icmpId", Integer.class);

    public final NumberPath<Integer> icmpType = createNumber("icmpType", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QDroidIpv4Packet ipv4Packet;

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidIcmpPacket(String variable) {
        this(DroidIcmpPacket.class, forVariable(variable), INITS);
    }

    public QDroidIcmpPacket(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidIcmpPacket(PathMetadata<?> metadata, PathInits inits) {
        this(DroidIcmpPacket.class, metadata, inits);
    }

    public QDroidIcmpPacket(Class<? extends DroidIcmpPacket> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ipv4Packet = inits.isInitialized("ipv4Packet") ? new QDroidIpv4Packet(forProperty("ipv4Packet"), inits.get("ipv4Packet")) : null;
    }

}

