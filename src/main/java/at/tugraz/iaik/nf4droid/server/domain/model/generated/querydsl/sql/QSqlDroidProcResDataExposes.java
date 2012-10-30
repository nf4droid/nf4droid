package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResDataExposes is a Querydsl query type for QSqlDroidProcResDataExposes
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResDataExposes extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResDataExposes> {

    private static final long serialVersionUID = 359527212;

    public static final QSqlDroidProcResDataExposes droidProcResDataExposes = new QSqlDroidProcResDataExposes("droid_proc_res_data_exposes");

    public final NumberPath<Long> dataExposeId = createNumber("data_expose_id", Long.class);

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDataExpose> fkfde9271f2b1080f6 = createForeignKey(dataExposeId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fkfde9271fe2ce265 = createForeignKey(droidProcResId, "id");

    public QSqlDroidProcResDataExposes(String variable) {
        super(QSqlDroidProcResDataExposes.class, forVariable(variable), "null", "droid_proc_res_data_exposes");
    }

    public QSqlDroidProcResDataExposes(Path<? extends QSqlDroidProcResDataExposes> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_data_exposes");
    }

    public QSqlDroidProcResDataExposes(PathMetadata<?> metadata) {
        super(QSqlDroidProcResDataExposes.class, metadata, "null", "droid_proc_res_data_exposes");
    }

}

