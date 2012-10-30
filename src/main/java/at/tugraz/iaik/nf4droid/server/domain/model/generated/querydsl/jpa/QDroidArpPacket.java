package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidArpPacket;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QDroidArpPacket is a Querydsl query type for DroidArpPacket
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidArpPacket extends EntityPathBase<DroidArpPacket> {

    private static final long serialVersionUID = 50675991;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidArpPacket droidArpPacket = new QDroidArpPacket("droidArpPacket");

    public final QDroidEthernetFrame ethernetFrame;

    public final NumberPath<Integer> hardwareSize = createNumber("hardwareSize", Integer.class);

    public final NumberPath<Integer> hardwareType = createNumber("hardwareType", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> opcode = createNumber("opcode", Integer.class);

    public final NumberPath<Integer> protocolSize = createNumber("protocolSize", Integer.class);

    public final NumberPath<Integer> protocolType = createNumber("protocolType", Integer.class);

    public final StringPath senderIp = createString("senderIp");

    public final StringPath senderMac = createString("senderMac");

    public final StringPath targetIp = createString("targetIp");

    public final StringPath targetMac = createString("targetMac");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidArpPacket(String variable) {
        this(DroidArpPacket.class, forVariable(variable), INITS);
    }

    public QDroidArpPacket(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidArpPacket(PathMetadata<?> metadata, PathInits inits) {
        this(DroidArpPacket.class, metadata, inits);
    }

    public QDroidArpPacket(Class<? extends DroidArpPacket> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ethernetFrame = inits.isInitialized("ethernetFrame") ? new QDroidEthernetFrame(forProperty("ethernetFrame"), inits.get("ethernetFrame")) : null;
    }

}

