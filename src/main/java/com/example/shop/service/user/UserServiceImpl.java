package com.example.shop.service.user;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.mapper.UserMapper;
import com.example.shop.model.UserDto;
import com.example.shop.persist.entity.UserEntity;
import com.example.shop.persist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        final UserEntity userEntity = mapper.save(createUserRequest);

        return mapper.convertEntityToDto(repository.save(userEntity));
    }
}
