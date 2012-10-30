package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResTcpCons is a Querydsl query type for QSqlDroidProcResTcpCons
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResTcpCons extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResTcpCons> {

    private static final long serialVersionUID = 2059837241;

    public static final QSqlDroidProcResTcpCons droidProcResTcpCons = new QSqlDroidProcResTcpCons("droid_proc_res_tcp_cons");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> tcpConId = createNumber("tcp_con_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidTcpConnection> fk6b716cd4e502e460 = createForeignKey(tcpConId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fk6b716cd4e2ce265 = createForeignKey(droidProcResId, "id");

    public QSqlDroidProcResTcpCons(String variable) {
        super(QSqlDroidProcResTcpCons.class, forVariable(variable), "null", "droid_proc_res_tcp_cons");
    }

    public QSqlDroidProcResTcpCons(Path<? extends QSqlDroidProcResTcpCons> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_tcp_cons");
    }

    public QSqlDroidProcResTcpCons(PathMetadata<?> metadata) {
        super(QSqlDroidProcResTcpCons.class, metadata, "null", "droid_proc_res_tcp_cons");
    }

}

