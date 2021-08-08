package com.icommerce.audit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.icommerce.audit.domain.AuditLog;
import com.icommerce.audit.dto.AuditLogDto;


@Mapper(componentModel = "spring")
public interface AuditLogMapper {
	
	AuditLogMapper INSTANCE = Mappers.getMapper(AuditLogMapper.class);
	
	AuditLogDto entityToDto(AuditLog auditLog);

	AuditLog dtoToEntity(AuditLogDto auditLogDto);
	
}
