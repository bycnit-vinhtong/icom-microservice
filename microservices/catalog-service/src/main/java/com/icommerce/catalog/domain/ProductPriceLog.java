package com.icommerce.catalog.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Table product log price change
 * 
 * @author Tong Thanh Vinh
 */
@Getter
@Setter
@Entity
@Table(name = "product_price_log")
public class ProductPriceLog implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** log Id */
    private Long id;
	
	@ManyToOne
	@JoinColumn(name="Product.id", nullable=false)
    private Product product;
	
	@Column(name = "new_price")
    private BigDecimal newPrice;
	
	@Column(name = "old_price")
    private BigDecimal oldPrice;

	@Column(name = "modified_by", length = 50)
	private String modifiedBy;

	@Column(name = "lmodified_date")
	private Instant modifiedDate = Instant.now();

}
