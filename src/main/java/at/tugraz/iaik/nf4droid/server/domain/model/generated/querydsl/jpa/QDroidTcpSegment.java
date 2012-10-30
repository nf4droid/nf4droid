package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpSegment;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;


/**
 * QDroidTcpSegment is a Querydsl query type for DroidTcpSegment
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidTcpSegment extends EntityPathBase<DroidTcpSegment> {

    private static final long serialVersionUID = 1959993538;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidTcpSegment droidTcpSegment = new QDroidTcpSegment("droidTcpSegment");

    public final BooleanPath ack = createBoolean("ack");

    public final NumberPath<Integer> ackNr = createNumber("ackNr", Integer.class);

    public final NumberPath<Integer> destPort = createNumber("destPort", Integer.class);

    public final EnumPath<at.tugraz.iaik.nf4droid.shared.constant.DroidTcpDirection> direction = createEnum("direction", at.tugraz.iaik.nf4droid.shared.constant.DroidTcpDirection.class);

    public final BooleanPath fin = createBoolean("fin");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QDroidIpv4Packet ipv4Packet;

    public final QDroidIpv6Packet ipv6Packet;

    public final BooleanPath psh = createBoolean("psh");

    public final NumberPath<Integer> relativeAckNr = createNumber("relativeAckNr", Integer.class);

    public final NumberPath<Integer> relativeSeqNr = createNumber("relativeSeqNr", Integer.class);

    public final BooleanPath rst = createBoolean("rst");

    public final NumberPath<Integer> seqNr = createNumber("seqNr", Integer.class);

    public final NumberPath<Integer> sourcePort = createNumber("sourcePort", Integer.class);

    public final BooleanPath syn = createBoolean("syn");

    public final NumberPath<Integer> tcpLength = createNumber("tcpLength", Integer.class);

    public final BooleanPath urg = createBoolean("urg");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidTcpSegment(String variable) {
        this(DroidTcpSegment.class, forVariable(variable), INITS);
    }

    public QDroidTcpSegment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidTcpSegment(PathMetadata<?> metadata, PathInits inits) {
        this(DroidTcpSegment.class, metadata, inits);
    }

    public QDroidTcpSegment(Class<? extends DroidTcpSegment> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ipv4Packet = inits.isInitialized("ipv4Packet") ? new QDroidIpv4Packet(forProperty("ipv4Packet"), inits.get("ipv4Packet")) : null;
        this.ipv6Packet = inits.isInitialized("ipv6Packet") ? new QDroidIpv6Packet(forProperty("ipv6Packet"), inits.get("ipv6Packet")) : null;
    }

}

