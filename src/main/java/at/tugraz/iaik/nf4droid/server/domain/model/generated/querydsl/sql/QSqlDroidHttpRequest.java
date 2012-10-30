package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlDroidHttpRequest is a Querydsl query type for QSqlDroidHttpRequest
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidHttpRequest extends com.mysema.query.sql.RelationalPathBase<QSqlDroidHttpRequest> {

    private static final long serialVersionUID = -1284840138;

    public static final QSqlDroidHttpRequest droidHttpRequest = new QSqlDroidHttpRequest("droid_http_request");

    public final NumberPath<Long> capture = createNumber("capture", Long.class);

    public final StringPath host = createString("host");

    public final NumberPath<Integer> httpMethod = createNumber("http_method", Integer.class);

    public final NumberPath<Integer> httpVersion = createNumber("http_version", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath localIp = createString("local_ip");

    public final NumberPath<Integer> localPort = createNumber("local_port", Integer.class);

    public final StringPath queryString = createString("query_string");

    public final StringPath remoteCity = createString("remote_city");

    public final StringPath remoteCountry = createString("remote_country");

    public final StringPath remoteIp = createString("remote_ip");

    public final NumberPath<Float> remoteLat = createNumber("remote_lat", Float.class);

    public final NumberPath<Float> remoteLon = createNumber("remote_lon", Float.class);

    public final NumberPath<Integer> remotePort = createNumber("remote_port", Integer.class);

    public final StringPath url = createString("url");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidHttpRequest> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlTrafficCapture> fk1003bebbd73e0616 = createForeignKey(capture, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpReqParams> _fk29cfa49b1896e834 = createInvForeignKey(id, "http_request_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDataExpose> _fk99170df91896e834 = createInvForeignKey(id, "http_request_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpReqHeaders> _fk6ed827111896e834 = createInvForeignKey(id, "http_request_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResHttpRequests> _fkb720dcb61896e834 = createInvForeignKey(id, "http_request_id");

    public QSqlDroidHttpRequest(String variable) {
        super(QSqlDroidHttpRequest.class, forVariable(variable), "null", "droid_http_request");
    }

    public QSqlDroidHttpRequest(Path<? extends QSqlDroidHttpRequest> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_http_request");
    }

    public QSqlDroidHttpRequest(PathMetadata<?> metadata) {
        super(QSqlDroidHttpRequest.class, metadata, "null", "droid_http_request");
    }

}

