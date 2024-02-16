package com.example.shop.persist.repository;

import com.example.shop.persist.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface NewsRepository extends JpaRepository<NewsEntity,UUID> {
}
