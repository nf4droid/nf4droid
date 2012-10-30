package at.tugraz.iaik.nf4droid.shared.dto.generated.querydsl.jpa;

import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.expr.NumberExpression;
import com.mysema.query.types.expr.StringExpression;

/**
 * at.tugraz.iaik.nf4droid.shared.dto.generated.querydsl.jpa.QBarChartStatisticsEntry is a Querydsl Projection type for BarChartStatisticsEntry
 */
public class QBarChartStatisticsEntry extends ConstructorExpression<at.tugraz.iaik.nf4droid.shared.dto.BarChartStatisticsEntry> {

    private static final long serialVersionUID = -1983135500;

    public QBarChartStatisticsEntry(StringExpression value, NumberExpression<Long> count) {
        super(at.tugraz.iaik.nf4droid.shared.dto.BarChartStatisticsEntry.class, new Class[]{String.class, long.class}, value, count);
    }

}

