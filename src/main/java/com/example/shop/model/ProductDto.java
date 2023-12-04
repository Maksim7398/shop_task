package com.example.shop.model;



<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
=======
>>>>>>> develop
import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class ProductDto {

    private final UUID id;
    @Nullable
<<<<<<< HEAD
    private String article;
    @Nullable
=======
>>>>>>> develop
    private final String title;
    @Nullable
    private final String description;
    @Nullable
    private final Category category;

    private final Double price;

    private Integer quantity;
    private final LocalDateTime lastQuantityChange;
    private final LocalDateTime createDate;
}
