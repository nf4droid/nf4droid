package at.tugraz.iaik.nf4droid.shared.constant;

public enum FilterOperation {
	AND("and"), OR("or"), EQUALS("equals"), NOT_EQUALS("not_equals"), LIKE("like"), NOT_LIKE("not_like");

	private final String operation;
	
	private FilterOperation(String operation){
		this.operation = operation;
	}
	
	public String toString() {
		return operation;
	}

	public static FilterOperation convertToFilterOperation(final String operation) {
		if (operation.equals(FilterOperation.AND.toString())) {
			return FilterOperation.AND;
		} else if (operation.equals(FilterOperation.OR.toString())) {
			return FilterOperation.OR;
		} else if (operation.equals(FilterOperation.EQUALS.toString())) {
			return FilterOperation.EQUALS;
		} else if (operation.equals(FilterOperation.NOT_EQUALS.toString())) {
			return FilterOperation.NOT_EQUALS;
		} else if (operation.equals(FilterOperation.LIKE.toString())) {
			return FilterOperation.LIKE;
		} else if (operation.equals(FilterOperation.NOT_LIKE.toString())) {
			return FilterOperation.NOT_LIKE;
		} else {
			return null;
		}
	}
}

