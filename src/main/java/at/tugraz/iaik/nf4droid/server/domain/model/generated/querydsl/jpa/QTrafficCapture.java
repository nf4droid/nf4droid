package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.ArrayPath;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QTrafficCapture is a Querydsl query type for TrafficCapture
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTrafficCapture extends EntityPathBase<TrafficCapture> {

    private static final long serialVersionUID = -990606603;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QTrafficCapture trafficCapture = new QTrafficCapture("trafficCapture");

    public final StringPath androidId = createString("androidId");

    public final QAppVersion appVersion;

    public final StringPath bssid = createString("bssid");

    public final DateTimePath<java.util.Date> dateAdded = createDateTime("dateAdded", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> imei = createNumber("imei", Long.class);

    public final NumberPath<Long> imsi = createNumber("imsi", Long.class);

    public final StringPath latitude = createString("latitude");

    public final StringPath password = createString("password");

    public final ArrayPath<Byte> pcapFile = createArray("pcapFile", Byte[].class);

    public final StringPath phoneNr = createString("phoneNr");

    public final QDroidProcessingResult processingResult;

    public final StringPath ssid = createString("ssid");

    public final StringPath user = createString("user");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QTrafficCapture(String variable) {
        this(TrafficCapture.class, forVariable(variable), INITS);
    }

    public QTrafficCapture(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTrafficCapture(PathMetadata<?> metadata, PathInits inits) {
        this(TrafficCapture.class, metadata, inits);
    }

    public QTrafficCapture(Class<? extends TrafficCapture> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.appVersion = inits.isInitialized("appVersion") ? new QAppVersion(forProperty("appVersion"), inits.get("appVersion")) : null;
        this.processingResult = inits.isInitialized("processingResult") ? new QDroidProcessingResult(forProperty("processingResult")) : null;
    }

}

