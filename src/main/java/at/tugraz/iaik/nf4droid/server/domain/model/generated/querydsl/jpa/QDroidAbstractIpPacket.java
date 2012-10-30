package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;


import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QDroidAbstractIpPacket is a Querydsl query type for DroidAbstractIpPacket
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QDroidAbstractIpPacket extends EntityPathBase<at.tugraz.iaik.nf4droid.server.domain.model.DroidAbstractIpPacket> {

    private static final long serialVersionUID = 938767233;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidAbstractIpPacket droidAbstractIpPacket = new QDroidAbstractIpPacket("droidAbstractIpPacket");

    public final StringPath destIp = createString("destIp");

    public final BooleanPath destIsLocal = createBoolean("destIsLocal");

    public final QDroidEthernetFrame ethernetFrame;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath remoteCity = createString("remoteCity");

    public final StringPath remoteCountry = createString("remoteCountry");

    public final NumberPath<Float> remoteLat = createNumber("remoteLat", Float.class);

    public final NumberPath<Float> remoteLon = createNumber("remoteLon", Float.class);

    public final StringPath sourceIp = createString("sourceIp");

    public final BooleanPath sourceIsLocal = createBoolean("sourceIsLocal");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidAbstractIpPacket(String variable) {
        this(at.tugraz.iaik.nf4droid.server.domain.model.DroidAbstractIpPacket.class, forVariable(variable), INITS);
    }

    public QDroidAbstractIpPacket(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidAbstractIpPacket(PathMetadata<?> metadata, PathInits inits) {
        this(at.tugraz.iaik.nf4droid.server.domain.model.DroidAbstractIpPacket.class, metadata, inits);
    }

    public QDroidAbstractIpPacket(Class<? extends at.tugraz.iaik.nf4droid.server.domain.model.DroidAbstractIpPacket> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ethernetFrame = inits.isInitialized("ethernetFrame") ? new QDroidEthernetFrame(forProperty("ethernetFrame"), inits.get("ethernetFrame")) : null;
    }

}

