package com.icommerce.audit.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icommerce.audit.constants.WebConstants;
import com.icommerce.audit.dto.AuditLogDto;
import com.icommerce.audit.service.AuditLogService;



@RestController
@RequestMapping(WebConstants.View.AUDIT)
public class AuditController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuditController.class);

	@Autowired
	AuditLogService auditLogService;

	/**
	 * Returns the list of log
	 *
	 * @return a list of logs
	 */
	@GetMapping
	public List<AuditLogDto> traceLogs() {
		
		LOG.info("Display all logs...");
		
		return auditLogService.getAll();
	}


}
