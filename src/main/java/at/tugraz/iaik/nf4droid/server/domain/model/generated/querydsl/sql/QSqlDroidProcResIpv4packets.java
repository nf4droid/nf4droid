package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResIpv4packets is a Querydsl query type for QSqlDroidProcResIpv4packets
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResIpv4packets extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResIpv4packets> {

    private static final long serialVersionUID = -287681427;

    public static final QSqlDroidProcResIpv4packets droidProcResIpv4packets = new QSqlDroidProcResIpv4packets("droid_proc_res_ipv4packets");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> ipv4PacketId = createNumber("ipv4_packet_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fke446a141e2ce265 = createForeignKey(droidProcResId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIpv4packet> fke446a14174c24d5a = createForeignKey(ipv4PacketId, "id");

    public QSqlDroidProcResIpv4packets(String variable) {
        super(QSqlDroidProcResIpv4packets.class, forVariable(variable), "null", "droid_proc_res_ipv4packets");
    }

    public QSqlDroidProcResIpv4packets(Path<? extends QSqlDroidProcResIpv4packets> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_ipv4packets");
    }

    public QSqlDroidProcResIpv4packets(PathMetadata<?> metadata) {
        super(QSqlDroidProcResIpv4packets.class, metadata, "null", "droid_proc_res_ipv4packets");
    }

}

