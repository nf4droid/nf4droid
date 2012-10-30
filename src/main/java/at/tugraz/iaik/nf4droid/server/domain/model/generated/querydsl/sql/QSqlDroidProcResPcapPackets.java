package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResPcapPackets is a Querydsl query type for QSqlDroidProcResPcapPackets
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResPcapPackets extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResPcapPackets> {

    private static final long serialVersionUID = 734165552;

    public static final QSqlDroidProcResPcapPackets droidProcResPcapPackets = new QSqlDroidProcResPcapPackets("droid_proc_res_pcap_packets");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> pcapPacketId = createNumber("pcap_packet_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fk30814893e2ce265 = createForeignKey(droidProcResId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidPcapPacket> fk30814893d0387a54 = createForeignKey(pcapPacketId, "id");

    public QSqlDroidProcResPcapPackets(String variable) {
        super(QSqlDroidProcResPcapPackets.class, forVariable(variable), "null", "droid_proc_res_pcap_packets");
    }

    public QSqlDroidProcResPcapPackets(Path<? extends QSqlDroidProcResPcapPackets> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_pcap_packets");
    }

    public QSqlDroidProcResPcapPackets(PathMetadata<?> metadata) {
        super(QSqlDroidProcResPcapPackets.class, metadata, "null", "droid_proc_res_pcap_packets");
    }

}

