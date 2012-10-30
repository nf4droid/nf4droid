package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResUdpPackets is a Querydsl query type for QSqlDroidProcResUdpPackets
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResUdpPackets extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResUdpPackets> {

    private static final long serialVersionUID = -1348937725;

    public static final QSqlDroidProcResUdpPackets droidProcResUdpPackets = new QSqlDroidProcResUdpPackets("droid_proc_res_udp_packets");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> udpPacketId = createNumber("udp_packet_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fk64c5a448e2ce265 = createForeignKey(droidProcResId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidUdpPacket> fk64c5a44815a46608 = createForeignKey(udpPacketId, "id");

    public QSqlDroidProcResUdpPackets(String variable) {
        super(QSqlDroidProcResUdpPackets.class, forVariable(variable), "null", "droid_proc_res_udp_packets");
    }

    public QSqlDroidProcResUdpPackets(Path<? extends QSqlDroidProcResUdpPackets> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_udp_packets");
    }

    public QSqlDroidProcResUdpPackets(PathMetadata<?> metadata) {
        super(QSqlDroidProcResUdpPackets.class, metadata, "null", "droid_proc_res_udp_packets");
    }

}

