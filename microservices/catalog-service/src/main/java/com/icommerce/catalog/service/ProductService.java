package com.icommerce.catalog.service;

import com.icommerce.catalog.dto.PageDto;
import com.icommerce.catalog.dto.ProductDto;
import com.icommerce.catalog.dto.SearchCriteria;

public interface ProductService {
	
	/**
	 * Create a product
	 * @param productId
	 * @return the product, if created successfull, else null
	 */
	ProductDto createProduct(ProductDto productDto);
	
	/**
	 * Update a product
	 * @param productId
	 * @return the product, if update successfull, else null
	 */
	ProductDto updateProduct(ProductDto productDto);
	

	/**
	 * Get product by ID
	 * @param productId
	 * @return the product, if found, else null
	 */
	ProductDto getProduct(long productId);

	/**
	 * Delete Product by ID
	 * @param productId
	 * @return void
	 */
	void deleteProduct(long productId);

	/**
	 * Find product by criterias
	 * @return  PageDto<ProductDto>
	 */
	PageDto<ProductDto> findProductsByCriterias(SearchCriteria searchCriteria);
	
	
	void testCrash();
	
}
