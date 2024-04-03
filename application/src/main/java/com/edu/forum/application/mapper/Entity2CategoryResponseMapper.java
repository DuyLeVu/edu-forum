package com.edu.forum.application.mapper;

import com.edu.forum.application.model.entity.Category;
import com.edu.forum.application.model.response.CategoryResponse;
import com.edu.forum.common.mapper.BeanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Entity2CategoryResponseMapper extends BeanMapper<Category, CategoryResponse> {
  Entity2CategoryResponseMapper INSTANCE = Mappers.getMapper(Entity2CategoryResponseMapper.class);
}
