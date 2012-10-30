package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResIcmpv6packets is a Querydsl query type for QSqlDroidProcResIcmpv6packets
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResIcmpv6packets extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResIcmpv6packets> {

    private static final long serialVersionUID = -402263531;

    public static final QSqlDroidProcResIcmpv6packets droidProcResIcmpv6packets = new QSqlDroidProcResIcmpv6packets("droid_proc_res_icmpv6packets");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> icmpv6PacketId = createNumber("icmpv6_packet_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIcmpv6packet> fk339259e9c139e74a = createForeignKey(icmpv6PacketId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fk339259e9e2ce265 = createForeignKey(droidProcResId, "id");

    public QSqlDroidProcResIcmpv6packets(String variable) {
        super(QSqlDroidProcResIcmpv6packets.class, forVariable(variable), "null", "droid_proc_res_icmpv6packets");
    }

    public QSqlDroidProcResIcmpv6packets(Path<? extends QSqlDroidProcResIcmpv6packets> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_icmpv6packets");
    }

    public QSqlDroidProcResIcmpv6packets(PathMetadata<?> metadata) {
        super(QSqlDroidProcResIcmpv6packets.class, metadata, "null", "droid_proc_res_icmpv6packets");
    }

}

