package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidIcmpv6packet is a Querydsl query type for QSqlDroidIcmpv6packet
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidIcmpv6packet extends com.mysema.query.sql.RelationalPathBase<QSqlDroidIcmpv6packet> {

    private static final long serialVersionUID = 319236054;

    public static final QSqlDroidIcmpv6packet droidIcmpv6packet = new QSqlDroidIcmpv6packet("droid_icmpv6packet");

    public final NumberPath<Integer> checksum = createNumber("checksum", Integer.class);

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Integer> icmpType = createNumber("icmp_type", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> ipv6packet = createNumber("ipv6packet", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidIcmpv6packet> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIpv6packet> fk87a44f28e314aeb3 = createForeignKey(ipv6packet, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResIcmpv6packets> _fk339259e9c139e74a = createInvForeignKey(id, "icmpv6_packet_id");

    public QSqlDroidIcmpv6packet(String variable) {
        super(QSqlDroidIcmpv6packet.class, forVariable(variable), "null", "droid_icmpv6packet");
    }

    public QSqlDroidIcmpv6packet(Path<? extends QSqlDroidIcmpv6packet> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_icmpv6packet");
    }

    public QSqlDroidIcmpv6packet(PathMetadata<?> metadata) {
        super(QSqlDroidIcmpv6packet.class, metadata, "null", "droid_icmpv6packet");
    }

}

