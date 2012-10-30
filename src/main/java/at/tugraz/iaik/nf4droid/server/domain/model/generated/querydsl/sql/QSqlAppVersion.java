package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlAppVersion is a Querydsl query type for QSqlAppVersion
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlAppVersion extends com.mysema.query.sql.RelationalPathBase<QSqlAppVersion> {

    private static final long serialVersionUID = 1635674146;

    public static final QSqlAppVersion appVersion = new QSqlAppVersion("app_version");

    public final NumberPath<Long> appId = createNumber("app_id", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final NumberPath<Integer> versionCode = createNumber("version_code", Integer.class);

    public final StringPath versionName = createString("version_name");

    public final com.mysema.query.sql.PrimaryKey<QSqlAppVersion> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlApp> fkca3e8cba8b5ad033 = createForeignKey(appId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlTrafficCapture> _fk45d24304722cb9fe = createInvForeignKey(id, "app_version_id");

    public QSqlAppVersion(String variable) {
        super(QSqlAppVersion.class, forVariable(variable), "null", "app_version");
    }

    public QSqlAppVersion(Path<? extends QSqlAppVersion> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "app_version");
    }

    public QSqlAppVersion(PathMetadata<?> metadata) {
        super(QSqlAppVersion.class, metadata, "null", "app_version");
    }

}

