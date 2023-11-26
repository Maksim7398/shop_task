package com.example.Shop_task1.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> , JpaSpecificationExecutor<Product> {

    @Override
    <S extends Product> S save(S entity);

    @Override
    void deleteById(UUID uuid);

    @Override
    Optional<Product> findById(UUID uuid);




}
