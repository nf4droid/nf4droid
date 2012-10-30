package at.tugraz.iaik.nf4droid.shared.dto.generated.querydsl.jpa;

import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.expr.EnumExpression;
import com.mysema.query.types.expr.NumberExpression;

/**
 * at.tugraz.iaik.nf4droid.shared.dto.generated.querydsl.jpa.QBarChartExposeEntry is a Querydsl Projection type for BarChartExposeEntry
 */
public class QBarChartExposeEntry extends ConstructorExpression<at.tugraz.iaik.nf4droid.shared.dto.BarChartExposeEntry> {

    private static final long serialVersionUID = -495676045;

    public QBarChartExposeEntry(NumberExpression<Long> value, EnumExpression<at.tugraz.iaik.nf4droid.shared.constant.ExposeType> exposeType) {
        super(at.tugraz.iaik.nf4droid.shared.dto.BarChartExposeEntry.class, new Class[]{long.class, at.tugraz.iaik.nf4droid.shared.constant.ExposeType.class}, value, exposeType);
    }

}

