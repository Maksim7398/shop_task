package com.example.Shop_task1.data;

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
}
