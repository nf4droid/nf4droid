package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.AppVersion;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.ListPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QAppVersion is a Querydsl query type for AppVersion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAppVersion extends EntityPathBase<AppVersion> {

    private static final long serialVersionUID = 625274339;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QAppVersion appVersion = new QAppVersion("appVersion");

    public final QApp app;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture, QTrafficCapture> trafficCaptures = this.<at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture, QTrafficCapture>createList("trafficCaptures", at.tugraz.iaik.nf4droid.server.domain.model.TrafficCapture.class, QTrafficCapture.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final NumberPath<Integer> versionCode = createNumber("versionCode", Integer.class);

    public final StringPath versionName = createString("versionName");

    public QAppVersion(String variable) {
        this(AppVersion.class, forVariable(variable), INITS);
    }

    public QAppVersion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAppVersion(PathMetadata<?> metadata, PathInits inits) {
        this(AppVersion.class, metadata, inits);
    }

    public QAppVersion(Class<? extends AppVersion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.app = inits.isInitialized("app") ? new QApp(forProperty("app")) : null;
    }

}

