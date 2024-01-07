package com.example.shop.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
public enum Category {
    BREAD("Хлеб"),
    VEGETABLES("Овощи"),
    FRUITS("Фрукты"),
    CONFECTIONERY("Кондитерские изделия"),
    MEAT("Мясо");
    private final String label;
    Category(String label) {
        this.label = label;
    }
    @JsonCreator
    public static Category fromName(String name) {
        for (Category category : Category.values()) {
            if (category.name().equals(name) || category.label.equals(name)) {
                return category;
            }
        }
        return null;
    }
    @JsonValue
    public String getLabel() {
        return label;
    }
}
