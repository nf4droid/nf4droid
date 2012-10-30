package at.tugraz.iaik.nf4droid.shared.dto;

import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.expr.NumberExpression;
import com.mysema.query.types.expr.StringExpression;

/**
 * at.tugraz.iaik.nf4droid.shared.dto.QTrafficAmountInfo is a Querydsl Projection type for TrafficAmountInfo
 */
public class QTrafficAmountInfo extends ConstructorExpression<TrafficAmountInfo> {

    private static final long serialVersionUID = 1769497075;

    public QTrafficAmountInfo(NumberExpression<Long> trafficAmount, NumberExpression<Long> packetCount) {
        super(TrafficAmountInfo.class, new Class[]{long.class, long.class}, trafficAmount, packetCount);
    }

    public QTrafficAmountInfo(NumberExpression<Long> trafficAmount, NumberExpression<Long> packetCount, StringExpression description) {
        super(TrafficAmountInfo.class, new Class[]{long.class, long.class, String.class}, trafficAmount, packetCount, description);
    }

}

