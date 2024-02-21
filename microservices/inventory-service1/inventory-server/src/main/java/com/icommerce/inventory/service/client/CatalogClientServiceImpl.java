package com.icommerce.inventory.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.icommerce.inventory.dto.ProductInfoResponseDto;
import com.icommerce.inventory.dto.ProductInfoResponseDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;

@Service
public class CatalogClientServiceImpl implements CatalogClientService{
	private static final Logger LOG = LoggerFactory.getLogger(CatalogClientService.class);

	@Autowired
	RestTemplate restTemplate;

    @Value("${app.catalog-service-host}")
	private String catalogServiceUri;

    @Cacheable(value = "product", key = "#id")
    @Override
    public ProductInfoResponseDto getProductById(Long id) {
        ProductInfoResponseDto product = restTemplate.getForObject(catalogServiceUri + "/back/product/" + id, ProductInfoResponseDto.class);
        return product;    
    }
    
}
