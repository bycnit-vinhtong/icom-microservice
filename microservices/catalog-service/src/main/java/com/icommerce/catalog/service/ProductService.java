package com.icommerce.catalog.service;

import com.icommerce.catalog.dto.PageDto;
import com.icommerce.catalog.dto.ProductInfoResponseDto;
import com.icommerce.catalog.dto.ProductShoppingResponseDto;
import com.icommerce.catalog.dto.SearchCriteria;

public interface ProductService {
	
	/**
	 * Create a product
	 * @param productId
	 * @return the product, if created successfull, else null
	 */
	ProductShoppingResponseDto createProduct(ProductShoppingResponseDto productDto);
	
	/**
	 * Update a product
	 * @param productId
	 * @return the product, if update successfull, else null
	 */
	ProductShoppingResponseDto updateProduct(ProductShoppingResponseDto productDto);
	

	/**
	 * Get product by ID
	 * @param productId
	 * @return the product, if found, else null
	 */
	ProductShoppingResponseDto getProduct(long productId);

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
	PageDto<ProductShoppingResponseDto> findProductsByCriterias(SearchCriteria searchCriteria);
	
	
	void testCrash();


	/**
	 * Get product info by ID for back office 
	 * @param productId
	 * @return the product, if found, else null
	 */
	ProductInfoResponseDto getProductInfo(long productId);
	
}
