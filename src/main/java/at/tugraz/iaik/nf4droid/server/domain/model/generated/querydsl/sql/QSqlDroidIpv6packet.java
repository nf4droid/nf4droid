package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlDroidIpv6packet is a Querydsl query type for QSqlDroidIpv6packet
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidIpv6packet extends com.mysema.query.sql.RelationalPathBase<QSqlDroidIpv6packet> {

    private static final long serialVersionUID = 451387136;

    public static final QSqlDroidIpv6packet droidIpv6packet = new QSqlDroidIpv6packet("droid_ipv6packet");

    public final StringPath destIp = createString("dest_ip");

    public final BooleanPath destIsLocal = createBoolean("dest_is_local");

    public final NumberPath<Long> ethernetFrame = createNumber("ethernet_frame", Long.class);

    public final NumberPath<Integer> flowLabel = createNumber("flow_label", Integer.class);

    public final NumberPath<Integer> hopLimit = createNumber("hop_limit", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> nextHeader = createNumber("next_header", Byte.class);

    public final NumberPath<Integer> payloadLength = createNumber("payload_length", Integer.class);

    public final StringPath remoteCity = createString("remote_city");

    public final StringPath remoteCountry = createString("remote_country");

    public final NumberPath<Float> remoteLat = createNumber("remote_lat", Float.class);

    public final NumberPath<Float> remoteLon = createNumber("remote_lon", Float.class);

    public final StringPath sourceIp = createString("source_ip");

    public final BooleanPath sourceIsLocal = createBoolean("source_is_local");

    public final NumberPath<Byte> trafficClass = createNumber("traffic_class", Byte.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidIpv6packet> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidEthernetFrame> fk4ea72d28ef9ce86 = createForeignKey(ethernetFrame, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidTcpSegment> _fk65b1c792e314aeb3 = createInvForeignKey(id, "ipv6packet");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResIpv6packets> _fkb408faff858295e = createInvForeignKey(id, "ipv6_packet_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidUdpPacket> _fk8ebefc69e314aeb3 = createInvForeignKey(id, "ipv6packet");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIcmpv6packet> _fk87a44f28e314aeb3 = createInvForeignKey(id, "ipv6packet");

    public QSqlDroidIpv6packet(String variable) {
        super(QSqlDroidIpv6packet.class, forVariable(variable), "null", "droid_ipv6packet");
    }

    public QSqlDroidIpv6packet(Path<? extends QSqlDroidIpv6packet> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_ipv6packet");
    }

    public QSqlDroidIpv6packet(PathMetadata<?> metadata) {
        super(QSqlDroidIpv6packet.class, metadata, "null", "droid_ipv6packet");
    }

}

