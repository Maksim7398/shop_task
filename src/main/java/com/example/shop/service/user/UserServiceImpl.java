package com.example.shop.service.user;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.exception.EmailExistException;
import com.example.shop.exception.NewsNotFoundException;
import com.example.shop.exception.UserNotFoundException;
import com.example.shop.mapper.UserMapper;
import com.example.shop.model.UserDto;
import com.example.shop.persist.entity.NewsEntity;
import com.example.shop.persist.entity.UserEntity;
import com.example.shop.persist.repository.NewsRepository;
import com.example.shop.persist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    private final NewsRepository newsRepository;

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        if (repository.existsByEmail(createUserRequest.getEmail())){
            throw new EmailExistException("user with this email already exists!");
        }
        final UserEntity userEntity = mapper.save(createUserRequest);

        return mapper.convertEntityToDto(repository.save(userEntity));
    }

    @Transactional
    @Override
    public void watchNews(final UUID userId, final UUID newsId) {
        final NewsEntity newsEntity = newsRepository.findById(newsId).orElseThrow(() ->
                new NewsNotFoundException("news with this id not found"));
        final UserEntity userEntity = repository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("user with this id not found"));
        userEntity.getWatchNews().add(newsEntity);
        repository.save(userEntity);
    }

    @Transactional
    @Override
    public void unwatchNews(final UUID userId, final UUID newsId) {
        final NewsEntity newsEntity = newsRepository.findById(newsId).orElseThrow(() ->
                new NewsNotFoundException("news with this id not found"));
        final UserEntity userEntity = repository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("user with this id not found"));
        userEntity.getWatchNews().remove(newsEntity);
        repository.save(userEntity);
    }
}
