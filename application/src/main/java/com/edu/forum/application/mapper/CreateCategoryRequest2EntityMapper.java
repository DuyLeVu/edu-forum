package com.edu.forum.application.mapper;

import com.edu.forum.application.model.entity.Category;
import com.edu.forum.application.model.request.CreateCategoryRequest;
import com.edu.forum.common.mapper.BeanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateCategoryRequest2EntityMapper extends BeanMapper<CreateCategoryRequest, Category> {
  CreateCategoryRequest2EntityMapper INSTANCE = Mappers.getMapper(CreateCategoryRequest2EntityMapper.class);

  @Mapping(target = "name", expression = "java(request.getName().trim())")
  @Mapping(target = "description", source = "request.description")
  Category map(CreateCategoryRequest request);
}
