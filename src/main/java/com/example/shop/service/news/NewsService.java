package com.example.shop.service.news;

import com.example.shop.controller.request.CreateNewsRequest;
import com.example.shop.model.NewsDto;

import java.util.List;
import java.util.UUID;

public interface NewsService {

    UUID createNews(CreateNewsRequest createNewsRequest);

    List<NewsDto> getUnwatchNewsForUser(UUID userId);
}
