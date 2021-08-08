package com.icommerce.audit.service;

import java.util.List;

import com.icommerce.audit.dto.AuditLogDto;

public interface AuditLogService {
	
	AuditLogDto createAuditLog(AuditLogDto auditLogDto);
	
	List<AuditLogDto> getAll();
	
}
