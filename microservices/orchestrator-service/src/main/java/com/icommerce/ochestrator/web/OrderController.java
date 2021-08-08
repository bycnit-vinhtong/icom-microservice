package com.icommerce.ochestrator.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icommerce.ochestrator.constants.WebConstants;
import com.icommerce.ochestrator.service.OrchestratorService;


@RestController
@RequestMapping(WebConstants.OCHESTRATOR)
@RefreshScope
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@Value("${app.message}")
	private String appMessage;
	
	@Autowired
	OrchestratorService orchestratorService;
	
	@GetMapping("/app-message")
    public String getServiceName() {
        return "Message [" + this.appMessage + "]";
    }	
	
}
