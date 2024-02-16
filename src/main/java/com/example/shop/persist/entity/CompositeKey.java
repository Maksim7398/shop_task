package com.example.shop.persist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositeKey implements Serializable {

    @Column(name = "user_id")
    private UUID userID;

    @Column(name = "news_id")
    private UUID newsID;
}