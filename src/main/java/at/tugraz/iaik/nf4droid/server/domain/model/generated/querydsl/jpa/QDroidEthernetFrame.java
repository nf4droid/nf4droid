package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidEthernetFrame;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QDroidEthernetFrame is a Querydsl query type for DroidEthernetFrame
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidEthernetFrame extends EntityPathBase<DroidEthernetFrame> {

    private static final long serialVersionUID = -483811034;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidEthernetFrame droidEthernetFrame = new QDroidEthernetFrame("droidEthernetFrame");

    public final StringPath destMac = createString("destMac");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QDroidPcapPacket pcapPacket;

    public final StringPath sourceMac = createString("sourceMac");

    public final EnumPath<at.tugraz.iaik.nf4droid.shared.constant.DroidEthernetType> type = createEnum("type", at.tugraz.iaik.nf4droid.shared.constant.DroidEthernetType.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidEthernetFrame(String variable) {
        this(DroidEthernetFrame.class, forVariable(variable), INITS);
    }

    public QDroidEthernetFrame(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidEthernetFrame(PathMetadata<?> metadata, PathInits inits) {
        this(DroidEthernetFrame.class, metadata, inits);
    }

    public QDroidEthernetFrame(Class<? extends DroidEthernetFrame> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pcapPacket = inits.isInitialized("pcapPacket") ? new QDroidPcapPacket(forProperty("pcapPacket")) : null;
    }

}

