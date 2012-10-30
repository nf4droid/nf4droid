package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidHttpRespHeaders is a Querydsl query type for QSqlDroidHttpRespHeaders
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidHttpRespHeaders extends com.mysema.query.sql.RelationalPathBase<QSqlDroidHttpRespHeaders> {

    private static final long serialVersionUID = -1766389571;

    public static final QSqlDroidHttpRespHeaders droidHttpRespHeaders = new QSqlDroidHttpRespHeaders("droid_http_resp_headers");

    public final NumberPath<Long> httpHeaderId = createNumber("http_header_id", Long.class);

    public final NumberPath<Long> httpResponseId = createNumber("http_response_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpResponse> fk2bbb916b26fb6480 = createForeignKey(httpResponseId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpHeaderField> fk2bbb916bfa4f32c6 = createForeignKey(httpHeaderId, "id");

    public QSqlDroidHttpRespHeaders(String variable) {
        super(QSqlDroidHttpRespHeaders.class, forVariable(variable), "null", "droid_http_resp_headers");
    }

    public QSqlDroidHttpRespHeaders(Path<? extends QSqlDroidHttpRespHeaders> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_http_resp_headers");
    }

    public QSqlDroidHttpRespHeaders(PathMetadata<?> metadata) {
        super(QSqlDroidHttpRespHeaders.class, metadata, "null", "droid_http_resp_headers");
    }

}

