package com.icommerce.inventory.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * CategoryDto data transfer object
 *
 * @author Tong Thanh Vinh
 */
@Data
public class CategoryDto implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** CategoryDto Id */
	private Long id;

	/** CategoryDto Name */
	private String name;

}
