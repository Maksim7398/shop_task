package com.example.shop.service.user;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.exception.NewsNotFoundException;
import com.example.shop.exception.UserNotFoundException;
import com.example.shop.mapper.UserMapper;
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
    public UserEntity createUser(final CreateUserRequest createUserRequest) {
        final UserEntity userEntity = mapper.save(createUserRequest);

        return repository.save(userEntity);
    }

    @Transactional
    @Override
    public void watchNews(final UUID user_id, final UUID news_id) {
        final NewsEntity newsEntity = newsRepository.findById(news_id).orElseThrow(() ->
                new NewsNotFoundException("news with this id not found"));
        final UserEntity userEntity = repository.findById(user_id).orElseThrow(() ->
                new UserNotFoundException("user with this id not found"));
        userEntity.getWatchNews().add(newsEntity);
        repository.save(userEntity);
    }

    @Transactional
    @Override
    public void unwatchNews(final UUID user_id, final UUID news_id) {
        final NewsEntity newsEntity = newsRepository.findById(news_id).orElseThrow(() ->
                new NewsNotFoundException("news with this id not found"));
        final UserEntity userEntity = repository.findById(user_id).orElseThrow(() ->
                new UserNotFoundException("user with this id not found"));
        userEntity.getWatchNews().remove(newsEntity);
        repository.save(userEntity);
    }
}