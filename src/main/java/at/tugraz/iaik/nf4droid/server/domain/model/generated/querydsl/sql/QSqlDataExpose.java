package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlDataExpose is a Querydsl query type for QSqlDataExpose
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDataExpose extends com.mysema.query.sql.RelationalPathBase<QSqlDataExpose> {

    private static final long serialVersionUID = 1031384249;

    public static final QSqlDataExpose dataExpose = new QSqlDataExpose("data_expose");

    public final NumberPath<Integer> exposeObscuring = createNumber("expose_obscuring", Integer.class);

    public final NumberPath<Integer> exposePoint = createNumber("expose_point", Integer.class);

    public final NumberPath<Integer> exposeType = createNumber("expose_type", Integer.class);

    public final StringPath exposedData = createString("exposed_data");

    public final NumberPath<Long> httpRequestId = createNumber("http_request_id", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> startTotalTrafficAmount = createNumber("start_total_traffic_amount", Long.class);

    public final NumberPath<Long> timeInstant = createNumber("time_instant", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDataExpose> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpRequest> fk99170df91896e834 = createForeignKey(httpRequestId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResDataExposes> _fkfde9271f2b1080f6 = createInvForeignKey(id, "data_expose_id");

    public QSqlDataExpose(String variable) {
        super(QSqlDataExpose.class, forVariable(variable), "null", "data_expose");
    }

    public QSqlDataExpose(Path<? extends QSqlDataExpose> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "data_expose");
    }

    public QSqlDataExpose(PathMetadata<?> metadata) {
        super(QSqlDataExpose.class, metadata, "null", "data_expose");
    }

}

