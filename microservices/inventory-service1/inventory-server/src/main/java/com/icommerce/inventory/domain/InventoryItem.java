package com.icommerce.inventory.domain;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "inventory")
public class InventoryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "product_code", nullable = false, unique = true)
	private String productCode;
	
	@Column(name = "product_id", nullable = false, unique = true)
	private Long productId;
	
	@Column(name = "quantity")
	private Integer availableQuantity = 0;
}
