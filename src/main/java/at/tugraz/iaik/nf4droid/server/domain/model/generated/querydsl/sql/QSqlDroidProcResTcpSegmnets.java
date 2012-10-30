package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResTcpSegmnets is a Querydsl query type for QSqlDroidProcResTcpSegmnets
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResTcpSegmnets extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResTcpSegmnets> {

    private static final long serialVersionUID = -863770314;

    public static final QSqlDroidProcResTcpSegmnets droidProcResTcpSegmnets = new QSqlDroidProcResTcpSegmnets("droid_proc_res_tcp_segmnets");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> tcpSegmId = createNumber("tcp_segm_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidTcpSegment> fka98871517edc65eb = createForeignKey(tcpSegmId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fka9887151e2ce265 = createForeignKey(droidProcResId, "id");

    public QSqlDroidProcResTcpSegmnets(String variable) {
        super(QSqlDroidProcResTcpSegmnets.class, forVariable(variable), "null", "droid_proc_res_tcp_segmnets");
    }

    public QSqlDroidProcResTcpSegmnets(Path<? extends QSqlDroidProcResTcpSegmnets> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_tcp_segmnets");
    }

    public QSqlDroidProcResTcpSegmnets(PathMetadata<?> metadata) {
        super(QSqlDroidProcResTcpSegmnets.class, metadata, "null", "droid_proc_res_tcp_segmnets");
    }

}

