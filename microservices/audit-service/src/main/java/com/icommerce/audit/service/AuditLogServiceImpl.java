package com.icommerce.audit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icommerce.audit.domain.AuditLog;
import com.icommerce.audit.dto.AuditLogDto;
import com.icommerce.audit.mapper.AuditLogMapper;
import com.icommerce.audit.repository.AuditLogRepository;


@Service
public class AuditLogServiceImpl implements AuditLogService {
	
	@Autowired
	AuditLogRepository auditLogRepository;
	
	@Autowired
	AuditLogMapper mapper;
	
	public AuditLogDto createAuditLog(AuditLogDto auditLogDto) {
		AuditLog log = mapper.dtoToEntity(auditLogDto);
		return mapper.entityToDto(auditLogRepository.save(log));
	}

	@Override
	public List<AuditLogDto> getAll() {
		List<AuditLogDto> result = new ArrayList<>();
		auditLogRepository.findAll().forEach(item -> {
			result.add(mapper.entityToDto(item));
		});
		
		return result;
	}
}
