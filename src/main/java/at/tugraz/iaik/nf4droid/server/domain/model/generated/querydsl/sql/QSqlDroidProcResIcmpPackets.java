package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResIcmpPackets is a Querydsl query type for QSqlDroidProcResIcmpPackets
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResIcmpPackets extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResIcmpPackets> {

    private static final long serialVersionUID = 213306101;

    public static final QSqlDroidProcResIcmpPackets droidProcResIcmpPackets = new QSqlDroidProcResIcmpPackets("droid_proc_res_icmp_packets");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> icmpPacketId = createNumber("icmp_packet_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIcmpPacket> fk6e17106e3007ba0a = createForeignKey(icmpPacketId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fk6e17106ee2ce265 = createForeignKey(droidProcResId, "id");

    public QSqlDroidProcResIcmpPackets(String variable) {
        super(QSqlDroidProcResIcmpPackets.class, forVariable(variable), "null", "droid_proc_res_icmp_packets");
    }

    public QSqlDroidProcResIcmpPackets(Path<? extends QSqlDroidProcResIcmpPackets> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_icmp_packets");
    }

    public QSqlDroidProcResIcmpPackets(PathMetadata<?> metadata) {
        super(QSqlDroidProcResIcmpPackets.class, metadata, "null", "droid_proc_res_icmp_packets");
    }

}

