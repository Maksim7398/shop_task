package com.example.shop.mapper;

import com.example.shop.controller.request.CreateNewsRequest;
import com.example.shop.controller.response.GetNewsResponse;
import com.example.shop.model.NewsDto;
import com.example.shop.persist.entity.NewsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsEntity newsEntity(NewsDto newsDto);

    NewsDto newsDto(CreateNewsRequest createNewsRequest);

    List<NewsDto> convertListEntityToDto(List<NewsEntity> newsEntityList);

    List<GetNewsResponse> convertListDtoToResponse(List<NewsDto> newsDtoList);
}
