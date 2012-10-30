package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlApp is a Querydsl query type for QSqlApp
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlApp extends com.mysema.query.sql.RelationalPathBase<QSqlApp> {

    private static final long serialVersionUID = -890245162;

    public static final QSqlApp app = new QSqlApp("app");

    public final StringPath appName = createString("app_name");

    public final StringPath appPackage = createString("app_package");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlApp> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlAppVersion> _fkca3e8cba8b5ad033 = createInvForeignKey(id, "app_id");

    public QSqlApp(String variable) {
        super(QSqlApp.class, forVariable(variable), "null", "app");
    }

    public QSqlApp(Path<? extends QSqlApp> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "app");
    }

    public QSqlApp(PathMetadata<?> metadata) {
        super(QSqlApp.class, metadata, "null", "app");
    }

}

