package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResHttpRequests is a Querydsl query type for QSqlDroidProcResHttpRequests
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResHttpRequests extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResHttpRequests> {

    private static final long serialVersionUID = -814814139;

    public static final QSqlDroidProcResHttpRequests droidProcResHttpRequests = new QSqlDroidProcResHttpRequests("droid_proc_res_http_requests");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> httpRequestId = createNumber("http_request_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fkb720dcb6e2ce265 = createForeignKey(droidProcResId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpRequest> fkb720dcb61896e834 = createForeignKey(httpRequestId, "id");

    public QSqlDroidProcResHttpRequests(String variable) {
        super(QSqlDroidProcResHttpRequests.class, forVariable(variable), "null", "droid_proc_res_http_requests");
    }

    public QSqlDroidProcResHttpRequests(Path<? extends QSqlDroidProcResHttpRequests> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_http_requests");
    }

    public QSqlDroidProcResHttpRequests(PathMetadata<?> metadata) {
        super(QSqlDroidProcResHttpRequests.class, metadata, "null", "droid_proc_res_http_requests");
    }

}

