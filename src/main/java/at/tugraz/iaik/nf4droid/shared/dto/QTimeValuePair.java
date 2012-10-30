package at.tugraz.iaik.nf4droid.shared.dto;

import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.expr.DateTimeExpression;
import com.mysema.query.types.expr.NumberExpression;

/**
 * at.tugraz.iaik.nf4droid.shared.dto.QTimeValuePair is a Querydsl Projection type for TimeValuePair
 */
public class QTimeValuePair extends ConstructorExpression<TimeValuePair> {

    private static final long serialVersionUID = 1881055886;

    public QTimeValuePair(NumberExpression<Long> time, NumberExpression<Long> value) {
        super(TimeValuePair.class, new Class[]{long.class, long.class}, time, value);
    }

    public QTimeValuePair(DateTimeExpression<? extends java.util.Date> time, NumberExpression<Long> value) {
        super(TimeValuePair.class, new Class[]{java.util.Date.class, long.class}, time, value);
    }

}

