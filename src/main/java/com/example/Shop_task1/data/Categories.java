package com.example.Shop_task1.data;

import com.fasterxml.jackson.annotation.*;



public enum Categories {
    BREAD("Хлеб"),
    VEGETABLES("Овощи"),
    FRUITS("Фрукты"),
    CONFECTIONERY("Кондитерские изделия"),
    MEAT("Мясо");

    private final String label;

    Categories(String label) {
        this.label = label;
    }

    @JsonCreator
    public static Categories fromName(String name) {
        for (Categories category : Categories.values()) {
            if (category.name().equals(name)) {
                return category;
            }
        } return null;
    }


    @JsonValue
    public String getLabel() {
        return label;
    }

}
