package com.icommerce.catalog.service;

import java.math.BigDecimal;

public interface ProductPriceLogService {
	/**
	 * Log price change into database
	 * @return void
	 */
	void logProductPriceChange(long productId, BigDecimal oldPrice, BigDecimal newPrice);
	
}
