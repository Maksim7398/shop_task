package com.example.shop.service.news;

import com.example.shop.exception.UserNotFoundException;
import com.example.shop.mapper.NewsMapper;
import com.example.shop.model.NewsDto;
import com.example.shop.persist.entity.NewsEntity;
import com.example.shop.persist.entity.UserEntity;
import com.example.shop.persist.repository.NewsRepository;
import com.example.shop.persist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    private final UserRepository userRepository;

    @Override
    public UUID createNews(final NewsDto newsDto) {
        final NewsEntity newsEntity = newsMapper.newsEntity(newsDto);
        newsEntity.setCreateDate(LocalDateTime.now());
        newsRepository.save(newsEntity);

        return newsEntity.getId();
    }
    @Transactional
    @Override
    public List<NewsDto> getUnwatchNewsForUser(final UUID userId) {
        final UserEntity userEntity = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("user with this id not found"));
        final List<NewsEntity> allNews = newsRepository.findAll();
        allNews.removeAll(userEntity.getWatchNews());

        return newsMapper.convertListEntityToDto(allNews);
    }
}
