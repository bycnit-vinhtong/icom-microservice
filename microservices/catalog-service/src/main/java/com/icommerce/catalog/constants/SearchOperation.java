package com.icommerce.catalog.constants;

/**
 * Enum used to express search operations
 *
 */
public enum SearchOperation {

    EQUALITY(":"), 
    NEGATION("!"),
    GREATER_THAN(">"), 
    LESS_THAN("<"), 
    LIKE("~");

	public final String operation;
	private SearchOperation(String operation) {
		this.operation = operation;
	}
}
