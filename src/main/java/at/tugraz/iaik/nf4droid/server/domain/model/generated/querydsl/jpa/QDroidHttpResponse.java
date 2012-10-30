package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpResponse;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.ListPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QDroidHttpResponse is a Querydsl query type for DroidHttpResponse
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidHttpResponse extends EntityPathBase<DroidHttpResponse> {

    private static final long serialVersionUID = -1683610215;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidHttpResponse droidHttpResponse = new QDroidHttpResponse("droidHttpResponse");

    public final QTrafficCapture capture;

    public final StringPath content = createString("content");

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField, QDroidHttpHeaderField> headerFields = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField, QDroidHttpHeaderField>createList("headerFields", at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField.class, QDroidHttpHeaderField.class);

    public final EnumPath<at.tugraz.iaik.nf4droid.shared.constant.DroidHttpVersion> httpVersion = createEnum("httpVersion", at.tugraz.iaik.nf4droid.shared.constant.DroidHttpVersion.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> statusCode = createNumber("statusCode", Integer.class);

    public final StringPath statusLine = createString("statusLine");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidHttpResponse(String variable) {
        this(DroidHttpResponse.class, forVariable(variable), INITS);
    }

    public QDroidHttpResponse(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidHttpResponse(PathMetadata<?> metadata, PathInits inits) {
        this(DroidHttpResponse.class, metadata, inits);
    }

    public QDroidHttpResponse(Class<? extends DroidHttpResponse> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.capture = inits.isInitialized("capture") ? new QTrafficCapture(forProperty("capture"), inits.get("capture")) : null;
    }

}

