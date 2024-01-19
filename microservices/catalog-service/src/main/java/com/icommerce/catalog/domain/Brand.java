package com.icommerce.catalog.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.Setter;


/**
 * Brand Tables contains information about Brand
 *
 * @author Tong Thanh Vinh
 */
@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** Brand Id */
	private Long id;
	
	@Column(name = "parent_id")
	private Long parentId;

	/** Brand Name */
	@Column(name = "name")
	private String name;
	
	
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

        final Brand other = (Brand) obj;

        return new EqualsBuilder().append(id, other.id).isEquals();
    }
}
