package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlDroidHttpResponse is a Querydsl query type for QSqlDroidHttpResponse
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidHttpResponse extends com.mysema.query.sql.RelationalPathBase<QSqlDroidHttpResponse> {

    private static final long serialVersionUID = -1122404742;

    public static final QSqlDroidHttpResponse droidHttpResponse = new QSqlDroidHttpResponse("droid_http_response");

    public final NumberPath<Long> capture = createNumber("capture", Long.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> httpVersion = createNumber("http_version", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> statusCode = createNumber("status_code", Integer.class);

    public final StringPath statusLine = createString("status_line");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidHttpResponse> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlTrafficCapture> fkf39bcd95d73e0616 = createForeignKey(capture, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResHttpResponses> _fk8ec995a05f3d88c0 = createInvForeignKey(id, "http_request_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpRespHeaders> _fk2bbb916b26fb6480 = createInvForeignKey(id, "http_response_id");

    public QSqlDroidHttpResponse(String variable) {
        super(QSqlDroidHttpResponse.class, forVariable(variable), "null", "droid_http_response");
    }

    public QSqlDroidHttpResponse(Path<? extends QSqlDroidHttpResponse> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_http_response");
    }

    public QSqlDroidHttpResponse(PathMetadata<?> metadata) {
        super(QSqlDroidHttpResponse.class, metadata, "null", "droid_http_response");
    }

}

