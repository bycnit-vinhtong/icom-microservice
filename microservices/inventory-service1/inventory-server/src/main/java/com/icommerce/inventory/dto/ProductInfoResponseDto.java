package com.icommerce.inventory.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * ProductShoppingResponseDto data transfer object
 *
 * @author Tong Thanh Vinh
 */
@Data
public class ProductInfoResponseDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String productCode;

	/** Product Name */
	private String name;

	/** Product Description */
	private String description;

	/** Product picture name */
	private String pictureFileName;

	/** Product picture url */
	private String pictureUri;

	/** Product Category */
	private String category;

	/** Product Brand */
	private String brand;
	
	/** Product Color */
	private String color;

	private Integer quantity;

}
