package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidPcapPacket is a Querydsl query type for QSqlDroidPcapPacket
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidPcapPacket extends com.mysema.query.sql.RelationalPathBase<QSqlDroidPcapPacket> {

    private static final long serialVersionUID = -2121941413;

    public static final QSqlDroidPcapPacket droidPcapPacket = new QSqlDroidPcapPacket("droid_pcap_packet");

    public final DateTimePath<java.sql.Timestamp> arrivalTime = createDateTime("arrival_time", java.sql.Timestamp.class);

    public final NumberPath<Long> arrivalTimeInstant = createNumber("arrival_time_instant", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> includedLength = createNumber("included_length", Long.class);

    public final NumberPath<Long> originalLength = createNumber("original_length", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidPcapPacket> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidEthernetFrame> _fkffe5572844395ec4 = createInvForeignKey(id, "pcap_packet");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResPcapPackets> _fk30814893d0387a54 = createInvForeignKey(id, "pcap_packet_id");

    public QSqlDroidPcapPacket(String variable) {
        super(QSqlDroidPcapPacket.class, forVariable(variable), "null", "droid_pcap_packet");
    }

    public QSqlDroidPcapPacket(Path<? extends QSqlDroidPcapPacket> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_pcap_packet");
    }

    public QSqlDroidPcapPacket(PathMetadata<?> metadata) {
        super(QSqlDroidPcapPacket.class, metadata, "null", "droid_pcap_packet");
    }

}

