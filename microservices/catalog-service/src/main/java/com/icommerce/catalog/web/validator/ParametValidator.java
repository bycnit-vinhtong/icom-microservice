package com.icommerce.catalog.web.validator;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.icommerce.catalog.constants.ProductField;
import com.icommerce.catalog.dto.SearchCriteria;
import com.icommerce.catalog.exception.InvalidInputException;

@Service
public class ParametValidator {
	public void validate(SearchCriteria criteria) {
		if (StringUtils.isNotEmpty(criteria.getSortField())) {
			List<String> availableSortField = Arrays.asList(ProductField.NAME.label, ProductField.PRICE.label);

			if (!availableSortField.contains(criteria.getSortField())) {
				throw new InvalidInputException(String.format("SortField is not correct, it must be  [ %s ]",
						String.join(", ", availableSortField)));
			}

			if (criteria.getSortOrder() != 0 && criteria.getSortOrder() != 1) {
				throw new InvalidInputException("SortOrder must be 1 (ASC) or 0 (DESC)");
			}
		}
		if (MapUtils.isNotEmpty(criteria.getFilters())) {
			List<String> availableSearchField = Arrays.asList(ProductField.BRAND.label, ProductField.CATEGORY.label,
					ProductField.COLOR.label, ProductField.PRICELOW.label, ProductField.PRICEHIGHT.label);
			criteria.getFilters().forEach((key, value) -> {
				if (!availableSearchField.contains(key)) {
					throw new InvalidInputException(String.format("filters key is not correct, it must be  [ %s ]",
							String.join(", ", availableSearchField)));
				}
				if (ProductField.PRICEHIGHT.label.equals(key) || ProductField.PRICELOW.label.equals(key)) {
					if (!NumberUtils.isParsable((String) value)) {
						throw new InvalidInputException(String.format("[%s] must be a number", key));
					}
				}
				if (ProductField.BRAND.label.equals(key) || ProductField.CATEGORY.label.equals(key)) {
					try {
						Integer.parseInt((String) value);
					} catch (NumberFormatException ex) {
						throw new InvalidInputException(String.format("[%s] must be a number", key));
					}

				}
			});

		}

	}
}
