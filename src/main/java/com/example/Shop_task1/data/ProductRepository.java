package com.example.Shop_task1.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> , JpaSpecificationExecutor<ProductEntity> {

    @Override
    List<ProductEntity> findAll();

    @Override
    <S extends ProductEntity> S save(S entity);

    @Override
    Optional<ProductEntity> findById(UUID uuid);

    @Override
    void deleteById(UUID uuid);


}
