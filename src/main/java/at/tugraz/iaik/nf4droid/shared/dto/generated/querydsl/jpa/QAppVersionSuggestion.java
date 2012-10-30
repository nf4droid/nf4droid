package at.tugraz.iaik.nf4droid.shared.dto.generated.querydsl.jpa;

import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.expr.NumberExpression;
import com.mysema.query.types.expr.StringExpression;

/**
 * at.tugraz.iaik.nf4droid.shared.dto.generated.querydsl.jpa.QAppVersionSuggestion is a Querydsl Projection type for AppVersionSuggestion
 */
public class QAppVersionSuggestion extends ConstructorExpression<at.tugraz.iaik.nf4droid.shared.dto.AppVersionSuggestion> {

    private static final long serialVersionUID = -72918037;

    public QAppVersionSuggestion(NumberExpression<Integer> appVersionCode, StringExpression appVersionName) {
        super(at.tugraz.iaik.nf4droid.shared.dto.AppVersionSuggestion.class, new Class[]{int.class, String.class}, appVersionCode, appVersionName);
    }

}

