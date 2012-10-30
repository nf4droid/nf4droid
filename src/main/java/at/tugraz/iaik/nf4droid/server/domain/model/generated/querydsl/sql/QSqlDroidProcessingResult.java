package at.tugraz.iaik.nf4droid.server.domain.model.generated.querydsl.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;


/**
 * QSqlDroidProcessingResult is a Querydsl query type for QSqlDroidProcessingResult
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSqlDroidProcessingResult extends com.mysema.query.sql.RelationalPathBase<QSqlDroidProcessingResult> {

    private static final long serialVersionUID = 2005718561;

    public static final QSqlDroidProcessingResult droidProcessingResult = new QSqlDroidProcessingResult("droid_processing_result");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSqlDroidProcessingResult> primary = createPrimaryKey(id);

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResEthernetFrames> _fkcb4fe269e2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResIcmpPackets> _fk6e17106ee2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlTrafficCapture> _fk45d2430446ae384e = createInvForeignKey(id, "processing_result");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResArpPackets> _fk1eebdce6e2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResTcpSegmnets> _fka9887151e2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResIpv4packets> _fke446a141e2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResUdpPackets> _fk64c5a448e2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResTcpCons> _fk6b716cd4e2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResIcmpv6packets> _fk339259e9e2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResPcapPackets> _fk30814893e2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResHttpRequests> _fkb720dcb6e2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResDataExposes> _fkfde9271fe2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResIpv6packets> _fkb408faffe2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public final com.mysema.query.sql.ForeignKey<QSqlDroidProcResHttpResponses> _fk8ec995a0e2ce265 = createInvForeignKey(id, "droid_proc_res_id");

    public QSqlDroidProcessingResult(String variable) {
        super(QSqlDroidProcessingResult.class, forVariable(variable), "null", "droid_processing_result");
    }

    public QSqlDroidProcessingResult(Path<? extends QSqlDroidProcessingResult> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "droid_processing_result");
    }

    public QSqlDroidProcessingResult(PathMetadata<?> metadata) {
        super(QSqlDroidProcessingResult.class, metadata, "null", "droid_processing_result");
    }

}

