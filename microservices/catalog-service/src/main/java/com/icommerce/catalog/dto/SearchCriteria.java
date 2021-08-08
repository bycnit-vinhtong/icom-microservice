package com.icommerce.catalog.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import lombok.NonNull;

@Data
public class SearchCriteria implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Search value */
    @NonNull
    private String keyWord;
    
    private int page;

    private int size;

    private String sortField;

    private int sortOrder;
    
    Map<String, Object> filters;
    
    @Override
    public String toString() {
        return "SearchCriteria {" +
                "keyWord='" + this.keyWord + '}';
    }
   
}
