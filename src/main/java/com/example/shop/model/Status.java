package com.example.shop.model;

import org.springframework.lang.Nullable;

public enum Status {
    CREATED,
    CANCELLED,
    APPROVED,
    DONE,
    REJECTED;

    public @Nullable
    static Status fromName(String name) {
        for (Status status : Status.values()) {
            if (name.contains(status.name())) {
                return status;
            }
        }
        return null;
    }

}
