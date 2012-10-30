package at.tugraz.iaik.nf4droid.shared.dto.generated.querydsl.jpa;

import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.expr.StringExpression;

/**
 * at.tugraz.iaik.nf4droid.shared.dto.generated.querydsl.jpa.QAppSuggestion is a Querydsl Projection type for AppSuggestion
 */
public class QAppSuggestion extends ConstructorExpression<at.tugraz.iaik.nf4droid.shared.dto.AppSuggestion> {

    private static final long serialVersionUID = -1000447499;

    public QAppSuggestion(StringExpression appPackage, StringExpression appName) {
        super(at.tugraz.iaik.nf4droid.shared.dto.AppSuggestion.class, new Class[]{String.class, String.class}, appPackage, appName);
    }

}

