package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QDroidHttpParameter is a Querydsl query type for DroidHttpParameter
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidHttpParameter extends EntityPathBase<DroidHttpParameter> {

    private static final long serialVersionUID = -1032729263;

    public static final QDroidHttpParameter droidHttpParameter = new QDroidHttpParameter("droidHttpParameter");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath parameterName = createString("parameterName");

    public final StringPath parameterValue = createString("parameterValue");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidHttpParameter(String variable) {
        super(DroidHttpParameter.class, forVariable(variable));
    }

    public QDroidHttpParameter(Path<? extends DroidHttpParameter> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QDroidHttpParameter(PathMetadata<?> metadata) {
        super(DroidHttpParameter.class, metadata);
    }

}

