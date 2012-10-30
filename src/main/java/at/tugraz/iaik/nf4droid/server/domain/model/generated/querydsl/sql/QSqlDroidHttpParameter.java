package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlDroidHttpParameter is a Querydsl query type for QSqlDroidHttpParameter
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidHttpParameter extends com.mysema.query.sql.RelationalPathBase<QSqlDroidHttpParameter> {

    private static final long serialVersionUID = -815228784;

    public static final QSqlDroidHttpParameter droidHttpParameter = new QSqlDroidHttpParameter("droid_http_parameter");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath parameterName = createString("parameter_name");

    public final StringPath parameterValue = createString("parameter_value");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidHttpParameter> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpReqParams> _fk29cfa49bf0654b74 = createInvForeignKey(id, "http_parameter_id");

    public QSqlDroidHttpParameter(String variable) {
        super(QSqlDroidHttpParameter.class, forVariable(variable), "null", "droid_http_parameter");
    }

    public QSqlDroidHttpParameter(Path<? extends QSqlDroidHttpParameter> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_http_parameter");
    }

    public QSqlDroidHttpParameter(PathMetadata<?> metadata) {
        super(QSqlDroidHttpParameter.class, metadata, "null", "droid_http_parameter");
    }

}

