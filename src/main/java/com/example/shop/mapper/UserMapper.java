package com.example.shop.mapper;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.persist.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User save(CreateUserRequest createUserRequest);

}
