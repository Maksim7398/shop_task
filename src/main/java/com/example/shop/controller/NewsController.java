package com.example.shop.controller;

import com.example.shop.controller.request.CreateNewsRequest;
import com.example.shop.controller.response.GetNewsResponse;
import com.example.shop.mapper.NewsMapper;
import com.example.shop.service.news.NewsService;
import com.example.shop.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    private final NewsMapper newsMapper;

    private final UserService userService;

    /**
     * Этот метод используется для создания новости
     *
     * @param createNewsRequest сущность создания новости
     * @return возращает uuid созданой новости
     */
    @PostMapping
    public UUID createNews(@RequestBody @Valid CreateNewsRequest createNewsRequest) {
        return newsService.createNews(createNewsRequest);
    }

    /**
     * Этот метод используется для олучения новостей для пользователя
     *
     * @param userId id по которому будут отображаться новости
     * @return возращает список новостей для пользователя
     */
    @GetMapping("/{userId}")
    public List<GetNewsResponse> getNewsForUser(@PathVariable UUID userId){
       return newsMapper.convertListDtoToResponse(newsService.getUnwatchNewsForUser(userId));
    }

    /**
     * Этот метод просматривает новость у пользователя
     *
     * @param userId - id пользователя по которому будет просмотренна новость
     * @param newsId - id новости которая будет просмотренна
     * @return id новости которая просмотренна
     */
    @PostMapping("/watch")
    public String watchNews(@RequestParam UUID userId, @RequestParam UUID newsId){
        userService.watchNews(userId, newsId);
        return "Новость " + newsId + " просмотрена";
    }

    /**
     * Этот метод отменяет просмотр новости у пользователя
     *
     * @param userId - id пользователя по которому будет отменёнён просмотр новости
     * @param newsId - id новости котрая будет помеченной не просмотренной
     * @return id новости у которой отменён просмотр
     */
    @PostMapping("/unwatch")
    public String unwatchNews(@RequestParam UUID userId, @RequestParam UUID newsId){
        userService.unwatchNews(userId, newsId);
        return "Новость " + newsId + " не просмотрена";
    }
}
