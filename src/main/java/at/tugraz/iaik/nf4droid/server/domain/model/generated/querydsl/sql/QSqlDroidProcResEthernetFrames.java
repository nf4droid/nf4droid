package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcResEthernetFrames is a Querydsl query type for QSqlDroidProcResEthernetFrames
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcResEthernetFrames extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcResEthernetFrames> {

    private static final long serialVersionUID = -1094133514;

    public static final QSqlDroidProcResEthernetFrames droidProcResEthernetFrames = new QSqlDroidProcResEthernetFrames("droid_proc_res_ethernet_frames");

    public final NumberPath<Long> droidProcResId = createNumber("droid_proc_res_id", Long.class);

    public final NumberPath<Long> ethFrameId = createNumber("eth_frame_id", Long.class);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidEthernetFrame> fkcb4fe269c8660894 = createForeignKey(ethFrameId, "id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcessingResult> fkcb4fe269e2ce265 = createForeignKey(droidProcResId, "id");

    public QSqlDroidProcResEthernetFrames(String variable) {
        super(QSqlDroidProcResEthernetFrames.class, forVariable(variable), "null", "droid_proc_res_ethernet_frames");
    }

    public QSqlDroidProcResEthernetFrames(Path<? extends QSqlDroidProcResEthernetFrames> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_proc_res_ethernet_frames");
    }

    public QSqlDroidProcResEthernetFrames(PathMetadata<?> metadata) {
        super(QSqlDroidProcResEthernetFrames.class, metadata, "null", "droid_proc_res_ethernet_frames");
    }

}

