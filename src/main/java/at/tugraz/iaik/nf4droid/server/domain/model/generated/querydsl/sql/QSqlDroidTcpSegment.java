package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidTcpSegment is a Querydsl query type for QSqlDroidTcpSegment
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidTcpSegment extends com.mysema.query.sql.RelationalPathBase<QSqlDroidTcpSegment> {

    private static final long serialVersionUID = -1896401437;

    public static final QSqlDroidTcpSegment droidTcpSegment = new QSqlDroidTcpSegment("droid_tcp_segment");

    public final BooleanPath ack = createBoolean("ack");

    public final NumberPath<Integer> ackNr = createNumber("ack_nr", Integer.class);

    public final NumberPath<Integer> destPort = createNumber("dest_port", Integer.class);

    public final NumberPath<Integer> direction = createNumber("direction", Integer.class);

    public final BooleanPath fin = createBoolean("fin");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> ipv4packet = createNumber("ipv4packet", Long.class);

    public final NumberPath<Long> ipv6packet = createNumber("ipv6packet", Long.class);

    public final BooleanPath psh = createBoolean("psh");

    public final NumberPath<Integer> relativeAckNr = createNumber("relative_ack_nr", Integer.class);

    public final NumberPath<Integer> relativeSeqNr = createNumber("relative_seq_nr", Integer.class);

    public final BooleanPath rst = createBoolean("rst");

    public final NumberPath<Integer> seqNr = createNumber("seq_nr", Integer.class);

    public final NumberPath<Integer> sourcePort = createNumber("source_port", Integer.class);

    public final BooleanPath syn = createBoolean("syn");

    public final NumberPath<Integer> tcpLength = createNumber("tcp_length", Integer.class);

    public final BooleanPath urg = createBoolean("urg");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidTcpSegment> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIpv6packet> fk65b1c792e314aeb3 = createForeignKey(ipv6packet, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidIpv4packet> fk65b1c792f7bc1af = createForeignKey(ipv4packet, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResTcpSegmnets> _fka98871517edc65eb = createInvForeignKey(id, "tcp_segm_id");

    public QSqlDroidTcpSegment(String variable) {
        super(QSqlDroidTcpSegment.class, forVariable(variable), "null", "droid_tcp_segment");
    }

    public QSqlDroidTcpSegment(Path<? extends QSqlDroidTcpSegment> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_tcp_segment");
    }

    public QSqlDroidTcpSegment(PathMetadata<?> metadata) {
        super(QSqlDroidTcpSegment.class, metadata, "null", "droid_tcp_segment");
    }

}

