package com.edu.forum.application.mapper;

import com.edu.forum.application.model.entity.Category;
import com.edu.forum.application.model.response.CreateCategoryResponse;
import com.edu.forum.common.mapper.BeanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Entity2CreateCategoryResponseMapper extends BeanMapper<Category, CreateCategoryResponse> {
  Entity2CreateCategoryResponseMapper INSTANCE = Mappers.getMapper(Entity2CreateCategoryResponseMapper.class);
}
