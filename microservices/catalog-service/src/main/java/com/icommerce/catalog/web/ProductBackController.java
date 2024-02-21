package com.icommerce.catalog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icommerce.catalog.constants.WebConstants;
import com.icommerce.catalog.dto.ProductInfoResponseDto;
import com.icommerce.catalog.service.ProductService;

@RestController
@RequestMapping(WebConstants.View.PRODUCTBACK)
@RefreshScope
public class ProductBackController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductBackController.class);

	@Autowired
	private ProductService service;

	@GetMapping("/{id}")
	// @PreAuthorize("hasRole('ui')")
	public ProductInfoResponseDto get(@PathVariable("id") final Long id) {
		LOG.info("Get product...{} ", id);
		return service.getProductInfo(id);
	}

}
