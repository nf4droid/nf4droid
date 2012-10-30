package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpRequest;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.ListPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QDroidHttpRequest is a Querydsl query type for DroidHttpRequest
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidHttpRequest extends EntityPathBase<DroidHttpRequest> {

    private static final long serialVersionUID = -1995680201;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDroidHttpRequest droidHttpRequest = new QDroidHttpRequest("droidHttpRequest");

    public final QTrafficCapture capture;

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DataExpose, QDataExpose> dataExposeList = this.<at.tugraz.iaik.nf4droid.server.domain.model.DataExpose, QDataExpose>createList("dataExposeList", at.tugraz.iaik.nf4droid.server.domain.model.DataExpose.class, QDataExpose.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField, QDroidHttpHeaderField> headerFields = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField, QDroidHttpHeaderField>createList("headerFields", at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpHeaderField.class, QDroidHttpHeaderField.class);

    public final StringPath host = createString("host");

    public final EnumPath<at.tugraz.iaik.nf4droid.shared.constant.DroidHttpMethod> httpMethod = createEnum("httpMethod", at.tugraz.iaik.nf4droid.shared.constant.DroidHttpMethod.class);

    public final EnumPath<at.tugraz.iaik.nf4droid.shared.constant.DroidHttpVersion> httpVersion = createEnum("httpVersion", at.tugraz.iaik.nf4droid.shared.constant.DroidHttpVersion.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath localIp = createString("localIp");

    public final NumberPath<Integer> localPort = createNumber("localPort", Integer.class);

    public final ListPath<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter, QDroidHttpParameter> parameters = this.<at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter, QDroidHttpParameter>createList("parameters", at.tugraz.iaik.nf4droid.server.domain.model.DroidHttpParameter.class, QDroidHttpParameter.class);

    public final StringPath queryString = createString("queryString");

    public final StringPath remoteCity = createString("remoteCity");

    public final StringPath remoteCountry = createString("remoteCountry");

    public final StringPath remoteIp = createString("remoteIp");

    public final NumberPath<Float> remoteLat = createNumber("remoteLat", Float.class);

    public final NumberPath<Float> remoteLon = createNumber("remoteLon", Float.class);

    public final NumberPath<Integer> remotePort = createNumber("remotePort", Integer.class);

    public final StringPath url = createString("url");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidHttpRequest(String variable) {
        this(DroidHttpRequest.class, forVariable(variable), INITS);
    }

    public QDroidHttpRequest(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDroidHttpRequest(PathMetadata<?> metadata, PathInits inits) {
        this(DroidHttpRequest.class, metadata, inits);
    }

    public QDroidHttpRequest(Class<? extends DroidHttpRequest> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.capture = inits.isInitialized("capture") ? new QTrafficCapture(forProperty("capture"), inits.get("capture")) : null;
    }

}

