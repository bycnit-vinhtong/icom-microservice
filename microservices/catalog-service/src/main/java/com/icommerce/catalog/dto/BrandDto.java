package com.icommerce.catalog.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Brand data transfer object
 *
 * @author Tong Thanh Vinh
 */
@Data
public class BrandDto implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** BrandDto Id */
	private Long id;
	
	/** BrandDto Id */
	private Long parentId;

	/** BrandDto Name */
	private String name;

}
