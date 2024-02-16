package com.example.shop.controller;

import com.example.shop.controller.request.CreateNewsRequest;
import com.example.shop.controller.response.GetNewsResponse;
import com.example.shop.mapper.NewsMapper;
import com.example.shop.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    private final NewsMapper newsMapper;

    @PostMapping("/news")
    public UUID createNews(@RequestBody CreateNewsRequest createNewsRequest) {
        return newsService.createNews(newsMapper.newsDto(createNewsRequest));
    }

    @GetMapping("/news/{user_id}")
    public List<GetNewsResponse> getNewsForUser(@PathVariable UUID user_id){
       return newsMapper.convertListDtoToResponse(newsService.getNewsForUser(user_id));
    }
}
