package com.icommerce.catalog.constants;

/**
 * Enum used to express product field
 *
 */
public enum ProductField {

	ID("id"),
    NAME("name"), 
    PRICE("price"),
    BRAND("brand"), 
    CATEGORY("category"), 
    COLOR("color"),
    PRICELOW("pricelow"),
    PRICEHIGHT("pricehight");

	public final String label;
	
	private ProductField(String label) {
		this.label = label;
	}
}
