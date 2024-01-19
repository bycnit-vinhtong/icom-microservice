package com.icommerce.catalog.repository.specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.icommerce.catalog.constants.ProductField;
import com.icommerce.catalog.domain.Brand;
import com.icommerce.catalog.domain.Category;
import com.icommerce.catalog.domain.Product;
import com.icommerce.catalog.dto.SearchCriteria;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductSpecification implements Specification<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NonNull
	private SearchCriteria criteria;

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Predicate predicateSearchByKeyword = null;

		if (StringUtils.isNotEmpty(criteria.getKeyWord())) {
			predicateSearchByKeyword = builder.and(builder.like(builder.upper(root.<String>get(ProductField.NAME.label)),
					"%" + criteria.getKeyWord().toUpperCase() + "%"));
		} else {
			predicateSearchByKeyword = builder.isTrue(builder.literal(true));
		}
		
		return builder.and(buildFilterQuery(predicateSearchByKeyword, query, root, builder));
	}

	private Predicate[] buildFilterQuery(Predicate predicateSearch, CriteriaQuery<?> criteriaQuery, Root<Product> root, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(predicateSearch);
		// Build predicates based on filter provided by user
		if (!criteria.getFilters().isEmpty()) {
			Object brandFetch;
			Object categoryFetch;
			
			//in case query count number of record, dont need to fetch join
			if (Long.class == criteriaQuery.getResultType()) {
				// Add join only
				brandFetch = root.join(ProductField.BRAND.label, JoinType.INNER);
				categoryFetch = root.join(ProductField.CATEGORY.label, JoinType.INNER);
			} else {
				brandFetch = root.fetch(ProductField.BRAND.label, JoinType.INNER);
				categoryFetch = root.fetch(ProductField.CATEGORY.label, JoinType.INNER);
			}

			Map<String, Object> filters = criteria.getFilters();

			filters.forEach((key, value) -> {
				switch (key) {
				case "brand":
					predicates.add(
							builder.and(builder.equal(((Join<Product, Brand>) brandFetch).get(ProductField.ID.label),
									Long.parseLong(value.toString()))));
					break;
				case "category":
					predicates.add(builder.equal(((Join<Product, Category>) categoryFetch).get(ProductField.ID.label),
							Long.parseLong(value.toString())));
					break;
				case "color":
					predicates.add(builder.equal(root.get(ProductField.COLOR.label), value.toString()));
					break;
				case "pricelow":
					predicates.add(builder.greaterThanOrEqualTo(root.get(ProductField.PRICE.label),
							Double.valueOf(value.toString())));
					break;
				case "pricehight":
					predicates.add(builder.lessThanOrEqualTo(root.get(ProductField.PRICE.label),
							Double.valueOf(value.toString())));
					break;

				default:
					break;
				}
			});

		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
