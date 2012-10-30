package at.tugraz.iaik.nf4droid.server.domain;

import java.util.Date;

import com.mysema.query.types.expr.CaseBuilder;
import com.mysema.query.types.expr.StringExpression;
import com.mysema.query.types.path.DateTimePath;

public class DomainUtil {

	public static StringExpression convertDateToDefaultFormatString(
			final DateTimePath<Date> datePath) {

		return datePath.year().stringValue().append("-")
				.append( new CaseBuilder()
					.when(datePath.month().stringValue().length().eq(1))
					.then(datePath.month().stringValue().prepend("0"))
					.otherwise(datePath.month().stringValue()))
				.append("-")
				.append(new CaseBuilder()
					.when(datePath.dayOfMonth().stringValue().length().eq(1))
					.then(datePath.dayOfMonth().stringValue().prepend("0"))
					.otherwise(datePath.dayOfMonth().stringValue()))
				.append(" ")
				.append(new CaseBuilder()
					.when(datePath.hour().stringValue().length().eq(1))
					.then(datePath.hour().stringValue().prepend("0"))
					.otherwise(datePath.hour().stringValue()))
				.append(":")
				.append(new CaseBuilder()
					.when(datePath.minute().stringValue().length().eq(1))
					.then(datePath.minute().stringValue().prepend("0"))
					.otherwise(datePath.minute().stringValue()));
	}

	public static String prepareSearchString(String searchString) {
		if (searchString != null && !searchString.isEmpty()) {

			searchString = searchString.replace('*', '%');
			if (searchString.charAt(0) != '%') {
				searchString = "%" + searchString;
			}
			if (searchString.charAt(searchString.length() - 1) != '%') {
				searchString = searchString + "%";
			}
			return searchString;
		} else {
			return null;
		}

	}

}
