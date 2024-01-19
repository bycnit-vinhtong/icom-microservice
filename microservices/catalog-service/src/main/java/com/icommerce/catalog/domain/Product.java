package com.icommerce.catalog.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * Product Tables contains information about Product
 *
 * @author Tong Thanh Vinh
 */
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    /** serialVersionUID */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** Product Id */
    private Long id;

    @Column(name = "product_code")
    private String productCode;
    
    /** Product Name */
    @Column(name = "name")
    private String name;
    
    
    @Transient
    private Integer quantity;
    
    /** Product Name */
    @Column(name = "price")
    private BigDecimal price;
    
    /** Price currency */
    @Column(name = "currency")
    private String currency;

    /** Product Description */
    @Column(name = "description ")
    private String description;

    /** Product picture name */
    @Column(name = "picture_file_name")
    private String pictureFileName;
    
    /** Product picture url */
    @Column(name = "picture_uri")
    private String pictureUri; 
    
    @ManyToOne
	@JoinColumn(name="category_id")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    
    @Column(name = "color")
    private String color; 


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        final Product other = (Product) obj;

        return new EqualsBuilder().append(id, other.id).isEquals();
    }
}
