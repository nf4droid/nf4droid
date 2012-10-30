package at.tugraz.iaik.nf4droid.shared.dto;

import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.expr.NumberExpression;
import com.mysema.query.types.expr.StringExpression;

/**
 * at.tugraz.iaik.nf4droid.shared.dto.QAppVersionSuggestion is a Querydsl Projection type for AppVersionSuggestion
 */
public class QAppVersionSuggestion extends ConstructorExpression<AppVersionSuggestion> {

    private static final long serialVersionUID = -72918037;

    public QAppVersionSuggestion(NumberExpression<Integer> appVersionCode, StringExpression appVersionName) {
        super(AppVersionSuggestion.class, new Class[]{int.class, String.class}, appVersionCode, appVersionName);
    }

}

