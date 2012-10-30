package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv6Packet;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QDroidIpv6Packet is a Querydsl query type for DroidIpv6Packet
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidIpv6Packet extends EntityPathBase<DroidIpv6Packet> {

    private static final long serialVersionUID = -903318017;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidIpv6Packet droidIpv6Packet = new QDroidIpv6Packet("droidIpv6Packet");

    public final QDroidAbstractIpPacket _super;

    //inherited
    public final StringPath destIp;

    //inherited
    public final BooleanPath destIsLocal;

    // inherited
    public final QDroidEthernetFrame ethernetFrame;

    public final NumberPath<Integer> flowLabel = createNumber("flowLabel", Integer.class);

    public final NumberPath<Integer> hopLimit = createNumber("hopLimit", Integer.class);

    //inherited
    public final NumberPath<Long> id;

    public final NumberPath<Byte> nextHeader = createNumber("nextHeader", Byte.class);

    public final NumberPath<Integer> payloadLength = createNumber("payloadLength", Integer.class);

    //inherited
    public final StringPath remoteCity;

    //inherited
    public final StringPath remoteCountry;

    //inherited
    public final NumberPath<Float> remoteLat;

    //inherited
    public final NumberPath<Float> remoteLon;

    //inherited
    public final StringPath sourceIp;

    //inherited
    public final BooleanPath sourceIsLocal;

    public final NumberPath<Byte> trafficClass = createNumber("trafficClass", Byte.class);

    //inherited
    public final NumberPath<Integer> version;

    public QDroidIpv6Packet(String variable) {
        this(DroidIpv6Packet.class, forVariable(variable), INITS);
    }

    public QDroidIpv6Packet(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidIpv6Packet(PathMetadata<?> metadata, PathInits inits) {
        this(DroidIpv6Packet.class, metadata, inits);
    }

    public QDroidIpv6Packet(Class<? extends DroidIpv6Packet> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QDroidAbstractIpPacket(type, metadata, inits);
        this.destIp = _super.destIp;
        this.destIsLocal = _super.destIsLocal;
        this.ethernetFrame = _super.ethernetFrame;
        this.id = _super.id;
        this.remoteCity = _super.remoteCity;
        this.remoteCountry = _super.remoteCountry;
        this.remoteLat = _super.remoteLat;
        this.remoteLon = _super.remoteLon;
        this.sourceIp = _super.sourceIp;
        this.sourceIsLocal = _super.sourceIsLocal;
        this.version = _super.version;
    }

}

