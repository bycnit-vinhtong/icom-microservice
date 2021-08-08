package com.icommerce.catalog.web;

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
import org.springframework.web.bind.annotation.RestController;

import com.icommerce.catalog.conf.RestTemplateConfig;
import com.icommerce.catalog.constants.WebConstants;
import com.icommerce.catalog.dto.PageDto;
import com.icommerce.catalog.dto.ProductDto;
import com.icommerce.catalog.dto.SearchCriteria;
import com.icommerce.catalog.service.ProductInventoryService;
import com.icommerce.catalog.service.ProductService;
import com.icommerce.catalog.web.validator.ParametValidator;
import com.icommerce.inventory.client.InventoryItemDto;


@RestController
@RequestMapping(WebConstants.View.PRODUCT)
@RefreshScope
public class ProductController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;
	
	@Autowired
	ParametValidator validator;
	
	@Value("${app.inventory-service.host}")
	private String inventoryServiceUri;
	
	
	@Value("${app.message}")
	private String appMessage;
	
	
	@Autowired
	ProductInventoryService productInventoryService;
	

	@Autowired
	private RestTemplateConfig restTemplateConfig;

	/**
	 * Find product by criteria
	 *
	 * @return a list of applications
	 */
	@PostMapping("find")
	public PageDto<ProductDto> findProducts(@RequestBody final SearchCriteria searchCriteria) {
		//validate input
		validator.validate(searchCriteria);
		
		LOG.info("Find Product by criteria product...{}", searchCriteria);
		return service.findProductsByCriterias(searchCriteria);
	}

	@GetMapping("/{id}")
	public ProductDto get(@PathVariable("id") final Long id) {
		LOG.info("Get product...{} ", id);
		return service.getProduct(id);
	}

	@GetMapping("/app-message")
    public String getServiceName() {
        return "Message [" + this.appMessage + "]";
    }
	
	
	@GetMapping("/test-inventory")
    public int getInvetoryByRestTeample() {
		return productInventoryService.getInventoryUsingRestTemplate(1L);
    }
	
	@GetMapping("/test-inventory1")
    public InventoryItemDto getInvetoryBySharingClientDto() {
		return productInventoryService.getInventoryUsingSharedClientPackage(1L);
    }
	
	
}
