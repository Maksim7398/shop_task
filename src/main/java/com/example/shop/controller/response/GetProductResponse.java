package com.example.shop.controller.response;

import com.example.shop.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
=======
>>>>>>> develop
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class GetProductResponse {
    private final UUID id;
<<<<<<< HEAD
    private final String article;
=======
>>>>>>> develop
    private final String title;
    private final String description;
    private final Category category;
    private final BigDecimal price;
    private Integer quantity;
    @JsonFormat(locale = "ru", pattern = "dd MMMM yyyy")
    private final LocalDateTime lastQuantityChange;
    private final LocalDateTime createDate;
}
