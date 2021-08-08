package com.icommerce.catalog.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * Category Tables contains information about Category
 *
 * @author Tong Thanh Vinh
 */
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/** Category Id */
	private Long id;
	
	@Column(name = "parent_id")
	private Long parentId;

	/** Category Name */
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

        final Category other = (Category) obj;

        return new EqualsBuilder().append(id, other.id).isEquals();
    }
}
