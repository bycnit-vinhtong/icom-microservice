package com.icommerce.catalog.web;

import org.apache.logging.log4j.util.Strings;
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
import com.icommerce.catalog.dto.ComputeResponse;
import com.icommerce.catalog.dto.PageDto;
import com.icommerce.catalog.dto.ProductDto;
import com.icommerce.catalog.dto.SearchCriteria;
import com.icommerce.catalog.service.ProductInventoryService;
import com.icommerce.catalog.service.ProductService;
import com.icommerce.catalog.web.validator.ParametValidator;
import com.icommerce.inventory.client.InventoryItemDto;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.security.access.prepost.PreAuthorize;

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
	//@PreAuthorize("hasRole('ui')")
	public ProductDto get(@PathVariable("id") final Long id) {
		LOG.info("Get product...{} ", id);
		return service.getProduct(id);
	}

	@GetMapping("/app-message")
    public String getServiceName() {
        return "Message [" + this.appMessage + "]";
    }
	
	
	@GetMapping("/test-inventory")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public int getInvetoryByRestTeample() {
		return productInventoryService.getInventoryUsingRestTemplate(1L);
    }
	
	@GetMapping("/test-inventory1")
    public InventoryItemDto getInvetoryBySharingClientDto() {
		return productInventoryService.getInventoryUsingSharedClientPackage(1L);
    }
	
	
	@GetMapping("/crash")
    public void testCrash() {
	    service.testCrash();
    }
    

	@GetMapping("/square/{input}")
    @RateLimiter(name = "squareLimit", fallbackMethod = "squareErrorResponse")
    public ComputeResponse getValue(@PathVariable int input){
        return ComputeResponse.of(input, input * input, ResponseType.SUCCESS, Strings.EMPTY);
    }

    public ComputeResponse squareErrorResponse(int input, Throwable throwable){
        return ComputeResponse.of(input, -1, ResponseType.FAILURE, throwable.getMessage());
    }

	public enum ResponseType {
		SUCCESS,
		FAILURE;
	}


	
}
