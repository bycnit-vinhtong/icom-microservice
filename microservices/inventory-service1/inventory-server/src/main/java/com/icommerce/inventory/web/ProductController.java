package com.icommerce.inventory.web;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icommerce.inventory.client.InventoryItemDto;
import com.icommerce.inventory.client.InventoryResponseDTO;
import com.icommerce.inventory.client.InventorytRequestDto;
import com.icommerce.inventory.dto.ProductInfoResponseDto;
import com.icommerce.inventory.service.InventoryItemService;
import com.icommerce.inventory.service.client.CatalogClientService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


@RestController
@RefreshScope
public class ProductController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	CatalogClientService catalogClientService;
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = "application/json")
	public ProductInfoResponseDto get(@PathVariable("id") final Long id) {
		LOG.info("Get product...{} ", id);
		return catalogClientService.getProductById(id);
	}

}
