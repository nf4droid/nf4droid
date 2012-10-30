package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidIpv4Packet;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QDroidIpv4Packet is a Querydsl query type for DroidIpv4Packet
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidIpv4Packet extends EntityPathBase<DroidIpv4Packet> {

    private static final long serialVersionUID = 1616641917;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidIpv4Packet droidIpv4Packet = new QDroidIpv4Packet("droidIpv4Packet");

    public final QDroidAbstractIpPacket _super;

    //inherited
    public final StringPath destIp;

    //inherited
    public final BooleanPath destIsLocal;

    // inherited
    public final QDroidEthernetFrame ethernetFrame;

    public final NumberPath<Integer> flags = createNumber("flags", Integer.class);

    public final NumberPath<Integer> fragmentOffset = createNumber("fragmentOffset", Integer.class);

    public final NumberPath<Integer> headerChecksum = createNumber("headerChecksum", Integer.class);

    public final NumberPath<Integer> headerLength = createNumber("headerLength", Integer.class);

    //inherited
    public final NumberPath<Long> id;

    public final NumberPath<Integer> identification = createNumber("identification", Integer.class);

    public final NumberPath<Integer> ipVersion = createNumber("ipVersion", Integer.class);

    public final NumberPath<Integer> protocol = createNumber("protocol", Integer.class);

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

    public final NumberPath<Integer> timeToLive = createNumber("timeToLive", Integer.class);

    public final NumberPath<Integer> totalLength = createNumber("totalLength", Integer.class);

    public final NumberPath<Integer> typeOfService = createNumber("typeOfService", Integer.class);

    //inherited
    public final NumberPath<Integer> version;

    public QDroidIpv4Packet(String variable) {
        this(DroidIpv4Packet.class, forVariable(variable), INITS);
    }

    public QDroidIpv4Packet(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidIpv4Packet(PathMetadata<?> metadata, PathInits inits) {
        this(DroidIpv4Packet.class, metadata, inits);
    }

    public QDroidIpv4Packet(Class<? extends DroidIpv4Packet> type, PathMetadata<?> metadata, PathInits inits) {
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

