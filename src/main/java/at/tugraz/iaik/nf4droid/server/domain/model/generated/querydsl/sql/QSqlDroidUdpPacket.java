package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidUdpPacket is a Querydsl query type for QSqlDroidUdpPacket
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidUdpPacket extends com.mysema.query.sql.RelationalPathBase<QSqlDroidUdpPacket> {

    private static final long serialVersionUID = 477215672;

    public static final QSqlDroidUdpPacket droidUdpPacket = new QSqlDroidUdpPacket("droid_udp_packet");

    public final NumberPath<Integer> checksum = createNumber("checksum", Integer.class);

    public final NumberPath<Integer> destPort = createNumber("dest_port", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> ipv4packet = createNumber("ipv4packet", Long.class);

    public final NumberPath<Long> ipv6packet = createNumber("ipv6packet", Long.class);

    public final NumberPath<Integer> length = createNumber("length", Integer.class);

    public final NumberPath<Integer> sourcePort = createNumber("source_port", Integer.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidUdpPacket> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIpv4packet> fk8ebefc69f7bc1af = createForeignKey(ipv4packet, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIpv6packet> fk8ebefc69e314aeb3 = createForeignKey(ipv6packet, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResUdpPackets> _fk64c5a44815a46608 = createInvForeignKey(id, "udp_packet_id");

    public QSqlDroidUdpPacket(String variable) {
        super(QSqlDroidUdpPacket.class, forVariable(variable), "null", "droid_udp_packet");
    }

    public QSqlDroidUdpPacket(Path<? extends QSqlDroidUdpPacket> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_udp_packet");
    }

    public QSqlDroidUdpPacket(PathMetadata<?> metadata) {
        super(QSqlDroidUdpPacket.class, metadata, "null", "droid_udp_packet");
    }

}

