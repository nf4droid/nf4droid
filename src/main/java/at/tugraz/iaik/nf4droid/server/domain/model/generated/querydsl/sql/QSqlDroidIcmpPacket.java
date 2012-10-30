package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidIcmpPacket is a Querydsl query type for QSqlDroidIcmpPacket
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidIcmpPacket extends com.mysema.query.sql.RelationalPathBase<QSqlDroidIcmpPacket> {

    private static final long serialVersionUID = -1307459338;

    public static final QSqlDroidIcmpPacket droidIcmpPacket = new QSqlDroidIcmpPacket("droid_icmp_packet");

    public final NumberPath<Integer> checksum = createNumber("checksum", Integer.class);

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Integer> icmpId = createNumber("icmp_id", Integer.class);

    public final NumberPath<Integer> icmpType = createNumber("icmp_type", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> ipv4packet = createNumber("ipv4packet", Long.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidIcmpPacket> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIpv4packet> fk19747187f7bc1af = createForeignKey(ipv4packet, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResIcmpPackets> _fk6e17106e3007ba0a = createInvForeignKey(id, "icmp_packet_id");

    public QSqlDroidIcmpPacket(String variable) {
        super(QSqlDroidIcmpPacket.class, forVariable(variable), "null", "droid_icmp_packet");
    }

    public QSqlDroidIcmpPacket(Path<? extends QSqlDroidIcmpPacket> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_icmp_packet");
    }

    public QSqlDroidIcmpPacket(PathMetadata<?> metadata) {
        super(QSqlDroidIcmpPacket.class, metadata, "null", "droid_icmp_packet");
    }

}

