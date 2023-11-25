package com.example.Shop_task1.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CRUDRepository extends CrudRepository<Product, UUID> {

    @Override
    <S extends Product> S save(S entity);

    @Override
    Iterable<Product> findAll();
}
