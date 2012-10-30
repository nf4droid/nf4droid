package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QSqlDroidTcpConnection is a Querydsl query type for QSqlDroidTcpConnection
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidTcpConnection extends com.mysema.query.sql.RelationalPathBase<QSqlDroidTcpConnection> {

    private static final long serialVersionUID = -1548604882;

    public static final QSqlDroidTcpConnection droidTcpConnection = new QSqlDroidTcpConnection("droid_tcp_connection");

    public final StringPath clientIp = createString("client_ip");

    public final NumberPath<Integer> clientPort = createNumber("client_port", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath serverCity = createString("server_city");

    public final StringPath serverCountry = createString("server_country");

    public final StringPath serverDomain = createString("server_domain");

    public final StringPath serverIp = createString("server_ip");

    public final NumberPath<Float> serverLatitude = createNumber("server_latitude", Float.class);

    public final NumberPath<Float> serverLongitude = createNumber("server_longitude", Float.class);

    public final NumberPath<Integer> serverPort = createNumber("server_port", Integer.class);

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidTcpConnection> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResTcpCons> _fk6b716cd4e502e460 = createInvForeignKey(id, "tcp_con_id");

    public QSqlDroidTcpConnection(String variable) {
        super(QSqlDroidTcpConnection.class, forVariable(variable), "null", "droid_tcp_connection");
    }

    public QSqlDroidTcpConnection(Path<? extends QSqlDroidTcpConnection> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_tcp_connection");
    }

    public QSqlDroidTcpConnection(PathMetadata<?> metadata) {
        super(QSqlDroidTcpConnection.class, metadata, "null", "droid_tcp_connection");
    }

}

