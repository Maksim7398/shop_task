package com.example.shop.service.user;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.mapper.UserMapper;
import com.example.shop.persist.entity.User;
import com.example.shop.persist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    @Override
    public User save(CreateUserRequest createUserRequest) {
        final User user = mapper.save(createUserRequest);

        return repository.save(user);
    }

}
