package at.tugraz.iaik.nf4droid.shared.dto;

import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.expr.NumberExpression;
import com.mysema.query.types.expr.StringExpression;

/**
 * at.tugraz.iaik.nf4droid.shared.dto.QBarChartStatisticsEntry is a Querydsl Projection type for BarChartStatisticsEntry
 */
public class QBarChartStatisticsEntry extends ConstructorExpression<BarChartStatisticsEntry> {

    private static final long serialVersionUID = -1983135500;

    public QBarChartStatisticsEntry(StringExpression value, NumberExpression<Long> count) {
        super(BarChartStatisticsEntry.class, new Class[]{String.class, long.class}, value, count);
    }

}

