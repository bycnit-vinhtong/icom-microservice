package com.icommerce.audit.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * AuditLogDto
 * 
 * @author Tong Thanh Vinh
 */
@Data
public class AuditLogDto implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

    /** log Id */
    private Long id;
	
    private String action;
    
    private String data;
	
	private LocalDateTime time;

}
