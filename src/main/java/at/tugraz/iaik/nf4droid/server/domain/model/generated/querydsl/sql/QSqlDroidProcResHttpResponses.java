package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResHttpResponses is a Querydsl query type for QSqlDroidProcResHttpResponses
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResHttpResponses extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResHttpResponses> {

    private static final long serialVersionUID = -2143455247;

    public static final QSqlDroidProcResHttpResponses droidProcResHttpResponses = new QSqlDroidProcResHttpResponses("droid_proc_res_http_responses");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> httpRequestId = createNumber("http_request_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpResponse> fk8ec995a05f3d88c0 = createForeignKey(httpRequestId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fk8ec995a0e2ce265 = createForeignKey(droidProcResId, "id");

    public QSqlDroidProcResHttpResponses(String variable) {
        super(QSqlDroidProcResHttpResponses.class, forVariable(variable), "null", "droid_proc_res_http_responses");
    }

    public QSqlDroidProcResHttpResponses(Path<? extends QSqlDroidProcResHttpResponses> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_http_responses");
    }

    public QSqlDroidProcResHttpResponses(PathMetadata<?> metadata) {
        super(QSqlDroidProcResHttpResponses.class, metadata, "null", "droid_proc_res_http_responses");
    }

}

