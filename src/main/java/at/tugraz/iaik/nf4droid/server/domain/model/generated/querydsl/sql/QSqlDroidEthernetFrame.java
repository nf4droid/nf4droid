package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlDroidEthernetFrame is a Querydsl query type for QSqlDroidEthernetFrame
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidEthernetFrame extends com.mysema.query.sql.RelationalPathBase<QSqlDroidEthernetFrame> {

    private static final long serialVersionUID = -266310555;

    public static final QSqlDroidEthernetFrame droidEthernetFrame = new QSqlDroidEthernetFrame("droid_ethernet_frame");

    public final StringPath destMac = createString("dest_mac");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> pcapPacket = createNumber("pcap_packet", Long.class);

    public final StringPath sourceMac = createString("source_mac");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidEthernetFrame> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidPcapPacket> fkffe5572844395ec4 = createForeignKey(pcapPacket, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidArpPacket> _fk94c0380b8ef9ce86 = createInvForeignKey(id, "ethernet_frame");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResEthernetFrames> _fkcb4fe269c8660894 = createInvForeignKey(id, "eth_frame_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIpv4packet> _fk9b1dfc508ef9ce86 = createInvForeignKey(id, "ethernet_frame");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIpv6packet> _fk4ea72d28ef9ce86 = createInvForeignKey(id, "ethernet_frame");

    public QSqlDroidEthernetFrame(String variable) {
        super(QSqlDroidEthernetFrame.class, forVariable(variable), "null", "droid_ethernet_frame");
    }

    public QSqlDroidEthernetFrame(Path<? extends QSqlDroidEthernetFrame> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_ethernet_frame");
    }

    public QSqlDroidEthernetFrame(PathMetadata<?> metadata) {
        super(QSqlDroidEthernetFrame.class, metadata, "null", "droid_ethernet_frame");
    }

}

