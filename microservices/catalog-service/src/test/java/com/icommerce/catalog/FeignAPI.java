package com.icommerce.catalog;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "inventory-service")
public interface FeignAPI {

   @RequestMapping(value = "inventory")
   String getInventory();

}
