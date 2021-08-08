package com.icommerce.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.icommerce.catalog.domain.Product;
import com.icommerce.catalog.domain.ProductPriceLog;

@Repository
public interface ProductPriceLogRepository extends JpaRepository<ProductPriceLog, Long>, JpaSpecificationExecutor<Product> {
	
}
