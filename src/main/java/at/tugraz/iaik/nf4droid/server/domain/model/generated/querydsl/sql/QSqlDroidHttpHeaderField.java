package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlDroidHttpHeaderField is a Querydsl query type for QSqlDroidHttpHeaderField
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidHttpHeaderField extends com.mysema.query.sql.RelationalPathBase<QSqlDroidHttpHeaderField> {

    private static final long serialVersionUID = -1925995116;

    public static final QSqlDroidHttpHeaderField droidHttpHeaderField = new QSqlDroidHttpHeaderField("droid_http_header_field");

    public final StringPath fieldName = createString("field_name");

    public final StringPath fieldValue = createString("field_value");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidHttpHeaderField> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpReqHeaders> _fk6ed82711fa4f32c6 = createInvForeignKey(id, "http_header_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidHttpRespHeaders> _fk2bbb916bfa4f32c6 = createInvForeignKey(id, "http_header_id");

    public QSqlDroidHttpHeaderField(String variable) {
        super(QSqlDroidHttpHeaderField.class, forVariable(variable), "null", "droid_http_header_field");
    }

    public QSqlDroidHttpHeaderField(Path<? extends QSqlDroidHttpHeaderField> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_http_header_field");
    }

    public QSqlDroidHttpHeaderField(PathMetadata<?> metadata) {
        super(QSqlDroidHttpHeaderField.class, metadata, "null", "droid_http_header_field");
    }

}

