package com.aegle.datatable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 * @since 1.0
 */
public abstract class FilterCriteriaBuilder<T> {
    private String jsonFilter;

    public FilterCriteriaBuilder(String jsonFilter) {
        this.jsonFilter = jsonFilter;
    }

    abstract List<javax.persistence.criteria.Predicate> getFilterPredicate(Root<T> fromOrder, CriteriaBuilder builder);

    Map<String, Object> getFilterMap() {
        Map<String, Object> filterMap = new HashMap<>();
        if (!jsonFilter.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                filterMap = mapper.readValue(jsonFilter, new TypeReference<Map<String, String>>() {
                });
            } catch (IOException e) {
                throw new FilterMapConversionException(e);
            }
        }
        return filterMap;
    }
}
