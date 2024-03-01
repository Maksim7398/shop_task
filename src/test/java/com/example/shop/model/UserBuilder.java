package com.example.shop.model;

import com.example.shop.persist.entity.UserEntity;

import java.util.UUID;

public class UserBuilder {

    public static final UUID DEFAULT_ID = UUID.randomUUID();

    public static final String DEFAULT_NAME = "USER";

    public static final String DEFAULT_EMAIL = "USER@mail.ru";

    private UUID id = DEFAULT_ID;

    private String name = DEFAULT_NAME;

    private String email = DEFAULT_EMAIL;

    private UserBuilder() {
    }

    public static UserBuilder aUserEntity() {
        return new UserBuilder();
    }

    public UserBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity build(){
        return UserEntity.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();
    }
}
