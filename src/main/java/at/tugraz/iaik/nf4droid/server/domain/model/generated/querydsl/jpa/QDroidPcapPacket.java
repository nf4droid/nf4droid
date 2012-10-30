package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.jpa;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import at.tugraz.iaik.nf4droid.server.domain.model.DroidPcapPacket;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;


/**
 * QDroidPcapPacket is a Querydsl query type for DroidPcapPacket
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDroidPcapPacket extends EntityPathBase<DroidPcapPacket> {

    private static final long serialVersionUID = 1734453562;

    public static final QDroidPcapPacket droidPcapPacket = new QDroidPcapPacket("droidPcapPacket");

    public final DateTimePath<org.joda.time.DateTime> arrivalTime = createDateTime("arrivalTime", org.joda.time.DateTime.class);

    public final DateTimePath<org.joda.time.Instant> arrivalTimeInstant = createDateTime("arrivalTimeInstant", org.joda.time.Instant.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> includedLength = createNumber("includedLength", Long.class);

    public final NumberPath<Long> originalLength = createNumber("originalLength", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDroidPcapPacket(String variable) {
        super(DroidPcapPacket.class, forVariable(variable));
    }

    public QDroidPcapPacket(Path<? extends DroidPcapPacket> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QDroidPcapPacket(PathMetadata<?> metadata) {
        super(DroidPcapPacket.class, metadata);
    }

}

