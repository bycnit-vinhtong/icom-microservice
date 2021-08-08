package com.icommerce.catalog.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@JoinColumn(name="Category.id")
    private Category category;
    
    @ManyToOne
	@JoinColumn(name="Brand.id")
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
