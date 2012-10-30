package at.tugraz.iaik.nf4droid.shared.dto;

import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.expr.EnumExpression;
import com.mysema.query.types.expr.NumberExpression;

/**
 * at.tugraz.iaik.nf4droid.shared.dto.QBarChartExposeEntry is a Querydsl Projection type for BarChartExposeEntry
 */
public class QBarChartExposeEntry extends ConstructorExpression<BarChartExposeEntry> {

    private static final long serialVersionUID = -495676045;

    public QBarChartExposeEntry(NumberExpression<Long> value, EnumExpression<at.tugraz.iaik.nf4droid.shared.constant.ExposeType> exposeType) {
        super(BarChartExposeEntry.class, new Class[]{long.class, at.tugraz.iaik.nf4droid.shared.constant.ExposeType.class}, value, exposeType);
    }

}

