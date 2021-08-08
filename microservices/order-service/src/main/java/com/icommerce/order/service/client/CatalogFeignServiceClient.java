package com.icommerce.order.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*import com.icommerce.category.client.ProductDto;

@FeignClient(name = "catalog-service", fallback = CatalogServiceClientFallback.class)
public interface CatalogFeignServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/product/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	ProductDto getProduct(@PathVariable("id") final Long productId);

}*/
