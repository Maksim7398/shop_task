package com.example.shop.persist.specification;

import com.example.shop.controller.request.SearchFilter;
import com.example.shop.persist.entity.ProductEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecification {
    public Specification<ProductEntity> getProduct(SearchFilter filter) {
        return ((root, query, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (filter.getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + filter.getTitle() + "%"));
            }
            if (filter.getPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), filter.getPrice()));
            }
            if (filter.getQuantity() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("quantity"), filter.getQuantity()));
            }
            if (filter.getIsAvailable() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isAvailable"), filter.getIsAvailable()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
