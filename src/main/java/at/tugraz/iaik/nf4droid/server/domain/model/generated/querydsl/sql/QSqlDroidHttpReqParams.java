package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidHttpReqParams is a Querydsl query type for QSqlDroidHttpReqParams
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidHttpReqParams extends com.mysema.query.sql.RelationalPathBase<QSqlDroidHttpReqParams> {

    private static final long serialVersionUID = 1156191403;

    public static final QSqlDroidHttpReqParams droidHttpReqParams = new QSqlDroidHttpReqParams("droid_http_req_params");

    public final NumberPath<Long> httpParameterId = createNumber("http_parameter_id", Long.class);

    public final NumberPath<Long> httpRequestId = createNumber("http_request_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpRequest> fk29cfa49b1896e834 = createForeignKey(httpRequestId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpParameter> fk29cfa49bf0654b74 = createForeignKey(httpParameterId, "id");

    public QSqlDroidHttpReqParams(String variable) {
        super(QSqlDroidHttpReqParams.class, forVariable(variable), "null", "droid_http_req_params");
    }

    public QSqlDroidHttpReqParams(Path<? extends QSqlDroidHttpReqParams> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_http_req_params");
    }

    public QSqlDroidHttpReqParams(PathMetadata<?> metadata) {
        super(QSqlDroidHttpReqParams.class, metadata, "null", "droid_http_req_params");
    }

}

