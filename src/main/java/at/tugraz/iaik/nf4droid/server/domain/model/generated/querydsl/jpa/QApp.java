package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.App;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.ListPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QApp is a Querydsl query type for App
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QApp extends EntityPathBase<App> {

    private static final long serialVersionUID = -869928651;

    public static final QApp app = new QApp("app");

    public final StringPath appName = createString("appName");

    public final StringPath appPackage = createString("appPackage");

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.AppVersion, QAppVersion> appVersions = this.<at.tugraz.iaik.nf4droid.server.domain.model.AppVersion, QAppVersion>createList("appVersions", at.tugraz.iaik.nf4droid.server.domain.model.AppVersion.class, QAppVersion.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QApp(String variable) {
        super(App.class, forVariable(variable));
    }

    public QApp(Path<? extends App> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QApp(PathMetadata<?> metadata) {
        super(App.class, metadata);
    }

}

