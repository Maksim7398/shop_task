package com.example.Shop_task1.data;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Categories {
    BREAD("Хлеб"),
    VEGETABLES("Овощи"),
    FRUITS("Фрукты"),
    CONFECTIONERY("Кондитерские изделия"),
    MEAT("Мясо");

    public final String label;


    Categories(String label) {
        this.label = label;
    }
    @JsonValue
    public String getLabel() {
        return label;
    }
}
