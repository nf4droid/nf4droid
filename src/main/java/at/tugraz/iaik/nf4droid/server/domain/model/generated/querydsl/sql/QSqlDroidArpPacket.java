package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlDroidArpPacket is a Querydsl query type for QSqlDroidArpPacket
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidArpPacket extends com.mysema.query.sql.RelationalPathBase<QSqlDroidArpPacket> {

    private static final long serialVersionUID = 896107478;

    public static final QSqlDroidArpPacket droidArpPacket = new QSqlDroidArpPacket("droid_arp_packet");

    public final NumberPath<Long> ethernetFrame = createNumber("ethernet_frame", Long.class);

    public final NumberPath<Integer> hardwareSize = createNumber("hardware_size", Integer.class);

    public final NumberPath<Integer> hardwareType = createNumber("hardware_type", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> opcode = createNumber("opcode", Integer.class);

    public final NumberPath<Integer> protocolSize = createNumber("protocol_size", Integer.class);

    public final NumberPath<Integer> protocolType = createNumber("protocol_type", Integer.class);

    public final StringPath senderIp = createString("sender_ip");

    public final StringPath senderMac = createString("sender_mac");

    public final StringPath targetIp = createString("target_ip");

    public final StringPath targetMac = createString("target_mac");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidArpPacket> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidEthernetFrame> fk94c0380b8ef9ce86 = createForeignKey(ethernetFrame, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResArpPackets> _fk1eebdce6f816b744 = createInvForeignKey(id, "arp_packet_id");

    public QSqlDroidArpPacket(String variable) {
        super(QSqlDroidArpPacket.class, forVariable(variable), "null", "droid_arp_packet");
    }

    public QSqlDroidArpPacket(Path<? extends QSqlDroidArpPacket> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_arp_packet");
    }

    public QSqlDroidArpPacket(PathMetadata<?> metadata) {
        super(QSqlDroidArpPacket.class, metadata, "null", "droid_arp_packet");
    }

}

