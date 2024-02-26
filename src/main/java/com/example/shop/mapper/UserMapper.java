package com.example.shop.mapper;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.model.UserDto;
import com.example.shop.persist.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity save(CreateUserRequest createUserRequest);

    UserDto convertEntityToDto(UserEntity userEntity);
}
