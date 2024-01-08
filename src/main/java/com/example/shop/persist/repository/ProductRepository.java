package com.example.shop.persist.repository;

import com.example.shop.persist.entity.ProductEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {
    boolean existsByArticle(String article);

    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<ProductEntity> findAll();
}