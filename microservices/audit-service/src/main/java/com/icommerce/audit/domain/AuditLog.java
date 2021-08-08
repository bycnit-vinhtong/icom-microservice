package com.icommerce.audit.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Table log
 * 
 * @author Tong Thanh Vinh
 */
@Getter
@Setter
@Entity
@Table(name = "audit_log")
public class AuditLog implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** log Id */
    private Long id;
	
	
	@Column(name = "action")
    private String action;
	

	@Column(name = "time")
	private LocalDateTime time;
	
	@Column(name = "data")
	private String data;
	

}
