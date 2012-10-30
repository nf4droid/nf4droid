package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResArpPackets is a Querydsl query type for QSqlDroidProcResArpPackets
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResArpPackets extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResArpPackets> {

    private static final long serialVersionUID = -1248193627;

    public static final QSqlDroidProcResArpPackets droidProcResArpPackets = new QSqlDroidProcResArpPackets("droid_proc_res_arp_packets");

    public final NumberPath<Long> arpPacketId = createNumber("arp_packet_id", Long.class);

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fk1eebdce6e2ce265 = createForeignKey(droidProcResId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidArpPacket> fk1eebdce6f816b744 = createForeignKey(arpPacketId, "id");

    public QSqlDroidProcResArpPackets(String variable) {
        super(QSqlDroidProcResArpPackets.class, forVariable(variable), "null", "droid_proc_res_arp_packets");
    }

    public QSqlDroidProcResArpPackets(Path<? extends QSqlDroidProcResArpPackets> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_arp_packets");
    }

    public QSqlDroidProcResArpPackets(PathMetadata<?> metadata) {
        super(QSqlDroidProcResArpPackets.class, metadata, "null", "droid_proc_res_arp_packets");
    }

}

