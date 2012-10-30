package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.SimplePath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlTrafficCapture is a Querydsl query type for QSqlTrafficCapture
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlTrafficCapture extends com.mysema.query.sql.RelationalPathBase<QSqlTrafficCapture> {

    private static final long serialVersionUID = -145175116;

    public static final QSqlTrafficCapture trafficCapture = new QSqlTrafficCapture("traffic_capture");

    public final StringPath androidId = createString("android_id");

    public final NumberPath<Long> appVersionId = createNumber("app_version_id", Long.class);

    public final StringPath bssid = createString("bssid");

    public final DateTimePath<java.sql.Timestamp> dateAdded = createDateTime("date_added", java.sql.Timestamp.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> imei = createNumber("imei", Long.class);

    public final NumberPath<Long> imsi = createNumber("imsi", Long.class);

    public final StringPath latitude = createString("latitude");

    public final StringPath password = createString("password");

    public final SimplePath<byte[]> pcapFile = createSimple("pcap_file", byte[].class);

    public final StringPath phoneNr = createString("phone_nr");

    public final NumberPath<Long> processingResult = createNumber("processing_result", Long.class);

    public final StringPath ssid = createString("ssid");

    public final StringPath user = createString("user");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlTrafficCapture> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlAppVersion> fk45d24304722cb9fe = createForeignKey(appVersionId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fk45d2430446ae384e = createForeignKey(processingResult, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpRequest> _fk1003bebbd73e0616 = createInvForeignKey(id, "capture");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpResponse> _fkf39bcd95d73e0616 = createInvForeignKey(id, "capture");

    public QSqlTrafficCapture(String variable) {
        super(QSqlTrafficCapture.class, forVariable(variable), "null", "traffic_capture");
    }

    public QSqlTrafficCapture(Path<? extends QSqlTrafficCapture> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "traffic_capture");
    }

    public QSqlTrafficCapture(PathMetadata<?> metadata) {
        super(QSqlTrafficCapture.class, metadata, "null", "traffic_capture");
    }

}

