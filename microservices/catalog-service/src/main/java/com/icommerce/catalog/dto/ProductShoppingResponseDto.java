package com.icommerce.catalog.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * ProductShoppingResponseDto data transfer object
 *
 * @author Tong Thanh Vinh
 */
@Data
public class ProductShoppingResponseDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String productCode;

	/** Product Name */
	private String name;
	
	/** Product quantity*/
	private Integer quantity;

	/** Product Name */
	private BigDecimal price;
	
	/** Price currency */
	private String currency;

	/** Product Description */
	private String description;

	/** Product picture name */
	private String pictureFileName;

	/** Product picture url */
	private String pictureUri;

	/** Product Category */
	private CategoryDto category;

	/** Product Brand */
	private BrandDto brand;
	
	/** Product Color */
	private String color;

}
