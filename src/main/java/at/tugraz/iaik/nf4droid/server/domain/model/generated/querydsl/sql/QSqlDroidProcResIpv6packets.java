package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResIpv6packets is a Querydsl query type for QSqlDroidProcResIpv6packets
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResIpv6packets extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResIpv6packets> {

    private static final long serialVersionUID = -1097028053;

    public static final QSqlDroidProcResIpv6packets droidProcResIpv6packets = new QSqlDroidProcResIpv6packets("droid_proc_res_ipv6packets");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> ipv6PacketId = createNumber("ipv6_packet_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIpv6packet> fkb408faff858295e = createForeignKey(ipv6PacketId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fkb408faffe2ce265 = createForeignKey(droidProcResId, "id");

    public QSqlDroidProcResIpv6packets(String variable) {
        super(QSqlDroidProcResIpv6packets.class, forVariable(variable), "null", "droid_proc_res_ipv6packets");
    }

    public QSqlDroidProcResIpv6packets(Path<? extends QSqlDroidProcResIpv6packets> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_ipv6packets");
    }

    public QSqlDroidProcResIpv6packets(PathMetadata<?> metadata) {
        super(QSqlDroidProcResIpv6packets.class, metadata, "null", "droid_proc_res_ipv6packets");
    }

}

