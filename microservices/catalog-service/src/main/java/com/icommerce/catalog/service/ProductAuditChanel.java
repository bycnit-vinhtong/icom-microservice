package com.icommerce.catalog.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductAuditChanel {
	
	String OUTPUT_AUDIT = "output-audit";
	
	@Output(OUTPUT_AUDIT)
	MessageChannel outputAudit();

}

