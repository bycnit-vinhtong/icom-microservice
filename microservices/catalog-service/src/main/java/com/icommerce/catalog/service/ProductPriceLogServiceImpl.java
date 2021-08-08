package com.icommerce.catalog.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.icommerce.catalog.domain.Product;
import com.icommerce.catalog.domain.ProductPriceLog;
import com.icommerce.catalog.repository.ProductPriceLogRepository;
import com.icommerce.catalog.repository.ProductRepository;

public class ProductPriceLogServiceImpl implements ProductPriceLogService {

	@Autowired
	ProductPriceLogRepository productPriceLogRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Transactional
	@Override
	public void logProductPriceChange(long productId, BigDecimal oldPrice, BigDecimal newPrice) {
		 ProductPriceLog  log = new ProductPriceLog();
		 Product product = productRepository.findById(productId).get();
		 log.setProduct(product);
		 log.setOldPrice(oldPrice);
		 log.setNewPrice(newPrice);
		 log.setModifiedBy("");
		 productPriceLogRepository.save(log);
	}

}
