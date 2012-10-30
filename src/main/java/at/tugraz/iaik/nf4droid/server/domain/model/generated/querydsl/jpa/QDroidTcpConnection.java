package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidTcpConnection;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QDroidTcpConnection is a Querydsl query type for DroidTcpConnection
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidTcpConnection extends EntityPathBase<DroidTcpConnection> {

    private static final long serialVersionUID = -1766105361;

    public static final QDroidTcpConnection droidTcpConnection = new QDroidTcpConnection("droidTcpConnection");

    public final StringPath clientIp = createString("clientIp");

    public final NumberPath<Integer> clientPort = createNumber("clientPort", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath serverCity = createString("serverCity");

    public final StringPath serverCountry = createString("serverCountry");

    public final StringPath serverDomain = createString("serverDomain");

    public final StringPath serverIp = createString("serverIp");

    public final NumberPath<Float> serverLatitude = createNumber("serverLatitude", Float.class);

    public final NumberPath<Float> serverLongitude = createNumber("serverLongitude", Float.class);

    public final NumberPath<Integer> serverPort = createNumber("serverPort", Integer.class);

    public final EnumPath<at.tugraz.iaik.nf4droid.shared.constant.DroidTcpState> state = createEnum("state", at.tugraz.iaik.nf4droid.shared.constant.DroidTcpState.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidTcpConnection(String variable) {
        super(DroidTcpConnection.class, forVariable(variable));
    }

    public QDroidTcpConnection(Path<? extends DroidTcpConnection> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QDroidTcpConnection(PathMetadata<?> metadata) {
        super(DroidTcpConnection.class, metadata);
    }

}

