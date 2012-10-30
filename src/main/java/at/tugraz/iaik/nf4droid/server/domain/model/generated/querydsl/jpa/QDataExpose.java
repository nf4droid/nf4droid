package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DataExpose;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QDataExpose is a Querydsl query type for DataExpose
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDataExpose extends EntityPathBase<DataExpose> {

    private static final long serialVersionUID = 20984442;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDataExpose dataExpose = new QDataExpose("dataExpose");

    public final StringPath exposedData = createString("exposedData");

    public final EnumPath<at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring> exposeObscuring = createEnum("exposeObscuring", at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring.class);

    public final EnumPath<at.tugraz.iaik.nf4droid.shared.constant.ExposePoint> exposePoint = createEnum("exposePoint", at.tugraz.iaik.nf4droid.shared.constant.ExposePoint.class);

    public final EnumPath<at.tugraz.iaik.nf4droid.shared.constant.ExposeType> exposeType = createEnum("exposeType", at.tugraz.iaik.nf4droid.shared.constant.ExposeType.class);

    public final QDroidHttpRequest httpRequest;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> startTotalTrafficAmount = createNumber("startTotalTrafficAmount", Long.class);

    public final DateTimePath<org.joda.time.Instant> timeInstant = createDateTime("timeInstant", org.joda.time.Instant.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDataExpose(String variable) {
        this(DataExpose.class, forVariable(variable), INITS);
    }

    public QDataExpose(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDataExpose(PathMetadata<?> metadata, PathInits inits) {
        this(DataExpose.class, metadata, inits);
    }

    public QDataExpose(Class<? extends DataExpose> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.httpRequest = inits.isInitialized("httpRequest") ? new QDroidHttpRequest(forProperty("httpRequest"), inits.get("httpRequest")) : null;
    }

}

