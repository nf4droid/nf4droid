package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidHttpReqHeaders is a Querydsl query type for QSqlDroidHttpReqHeaders
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidHttpReqHeaders extends com.mysema.query.sql.RelationalPathBase<QSqlDroidHttpReqHeaders> {

    private static final long serialVersionUID = -1223968511;

    public static final QSqlDroidHttpReqHeaders droidHttpReqHeaders = new QSqlDroidHttpReqHeaders("droid_http_req_headers");

    public final NumberPath<Long> httpHeaderId = createNumber("http_header_id", Long.class);

    public final NumberPath<Long> httpRequestId = createNumber("http_request_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpRequest> fk6ed827111896e834 = createForeignKey(httpRequestId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpHeaderField> fk6ed82711fa4f32c6 = createForeignKey(httpHeaderId, "id");

    public QSqlDroidHttpReqHeaders(String variable) {
        super(QSqlDroidHttpReqHeaders.class, forVariable(variable), "null", "droid_http_req_headers");
    }

    public QSqlDroidHttpReqHeaders(Path<? extends QSqlDroidHttpReqHeaders> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_http_req_headers");
    }

    public QSqlDroidHttpReqHeaders(PathMetadata<?> metadata) {
        super(QSqlDroidHttpReqHeaders.class, metadata, "null", "droid_http_req_headers");
    }

}

