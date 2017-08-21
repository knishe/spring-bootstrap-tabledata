package com.aegle.datatable;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 * @since 1.0
 */
class TableDataBuilder<T> {
    private SearchCriteria searchCriteria;
    private EntityManager entityManager;
    private FilterCriteriaBuilder<T> filterCriteria;

    List<Predicate> criteriaList = new ArrayList<>();

    TableDataBuilder(EntityManager entityManager, SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
        this.entityManager = entityManager;
    }

    TableData<T> getTableData(Class<T> entity) {
        return getTableData(entity, new FilterCriteriaBuilder(searchCriteria.getFilter()) {

            @Override
            List<Predicate> getFilterPredicate(Root fromOrder, CriteriaBuilder builder) {
                return new ArrayList<>();
            }
        });
    }

    TableData<T> getTableData(Class<T> entity, FilterCriteriaBuilder filterCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
        Root<T> from = criteriaQuery.from(entity);
        CriteriaQuery<T> select = criteriaQuery.select(from);

        String sort = searchCriteria.getSort();
        String order = searchCriteria.getOrder();
        if (StringUtils.isNotEmpty(sort)) {
            if (!StringUtils.isEmpty(order) && order.equalsIgnoreCase("DESC")) {
                criteriaQuery.orderBy(criteriaBuilder.desc(from
                        .get(sort)));
            } else {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(from.get(sort)));
            }
        }

        criteriaList.addAll(filterCriteria.getFilterPredicate(from, criteriaBuilder));
        criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[criteriaList.size()])));

        TypedQuery<T> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult(searchCriteria.getOffset());
        typedQuery.setMaxResults(searchCriteria.getLimit());
        List<T> resultList = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(entity)));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new TableData<>(resultList, count);
    }
}