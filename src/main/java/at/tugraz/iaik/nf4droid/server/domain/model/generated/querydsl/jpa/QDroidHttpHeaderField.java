package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QDroidHttpHeaderField is a Querydsl query type for DroidHttpHeaderField
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidHttpHeaderField extends EntityPathBase<DroidHttpHeaderField> {

    private static final long serialVersionUID = -490557931;

    public static final QDroidHttpHeaderField droidHttpHeaderField = new QDroidHttpHeaderField("droidHttpHeaderField");

    public final StringPath fieldName = createString("fieldName");

    public final StringPath fieldValue = createString("fieldValue");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidHttpHeaderField(String variable) {
        super(DroidHttpHeaderField.class, forVariable(variable));
    }

    public QDroidHttpHeaderField(Path<? extends DroidHttpHeaderField> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QDroidHttpHeaderField(PathMetadata<?> metadata) {
        super(DroidHttpHeaderField.class, metadata);
    }

}

