package com.icommerce.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.icommerce.order.constants.OrderStatus;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "purchase_order")
@ToString
public class Order {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "product_id")
	private Long productId;
	 
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "price")
	private Double price;
	
	private OrderStatus status;

}
