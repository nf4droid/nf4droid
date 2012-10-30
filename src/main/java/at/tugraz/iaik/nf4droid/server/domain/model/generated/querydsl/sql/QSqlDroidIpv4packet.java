package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlDroidIpv4packet is a Querydsl query type for QSqlDroidIpv4packet
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidIpv4packet extends com.mysema.query.sql.RelationalPathBase<QSqlDroidIpv4packet> {

    private static final long serialVersionUID = -1323620226;

    public static final QSqlDroidIpv4packet droidIpv4packet = new QSqlDroidIpv4packet("droid_ipv4packet");

    public final StringPath destIp = createString("dest_ip");

    public final BooleanPath destIsLocal = createBoolean("dest_is_local");

    public final NumberPath<Long> ethernetFrame = createNumber("ethernet_frame", Long.class);

    public final NumberPath<Integer> flags = createNumber("flags", Integer.class);

    public final NumberPath<Integer> fragmentOffset = createNumber("fragment_offset", Integer.class);

    public final NumberPath<Integer> headerChecksum = createNumber("header_checksum", Integer.class);

    public final NumberPath<Integer> headerLength = createNumber("header_length", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> identification = createNumber("identification", Integer.class);

    public final NumberPath<Integer> ipVersion = createNumber("ip_version", Integer.class);

    public final NumberPath<Integer> protocol = createNumber("protocol", Integer.class);

    public final StringPath remoteCity = createString("remote_city");

    public final StringPath remoteCountry = createString("remote_country");

    public final NumberPath<Float> remoteLat = createNumber("remote_lat", Float.class);

    public final NumberPath<Float> remoteLon = createNumber("remote_lon", Float.class);

    public final StringPath sourceIp = createString("source_ip");

    public final BooleanPath sourceIsLocal = createBoolean("source_is_local");

    public final NumberPath<Integer> timeToLive = createNumber("time_to_live", Integer.class);

    public final NumberPath<Integer> totalLength = createNumber("total_length", Integer.class);

    public final NumberPath<Integer> typeOfService = createNumber("type_of_service", Integer.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidIpv4packet> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidEthernetFrame> fk9b1dfc508ef9ce86 = createForeignKey(ethernetFrame, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidUdpPacket> _fk8ebefc69f7bc1af = createInvForeignKey(id, "ipv4packet");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResIpv4packets> _fke446a14174c24d5a = createInvForeignKey(id, "ipv4_packet_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIcmpPacket> _fk19747187f7bc1af = createInvForeignKey(id, "ipv4packet");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidTcpSegment> _fk65b1c792f7bc1af = createInvForeignKey(id, "ipv4packet");

    public QSqlDroidIpv4packet(String variable) {
        super(QSqlDroidIpv4packet.class, forVariable(variable), "null", "droid_ipv4packet");
    }

    public QSqlDroidIpv4packet(Path<? extends QSqlDroidIpv4packet> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_ipv4packet");
    }

    public QSqlDroidIpv4packet(PathMetadata<?> metadata) {
        super(QSqlDroidIpv4packet.class, metadata, "null", "droid_ipv4packet");
    }

}

