package com.edu.forum.application.mapper;

import com.edu.forum.application.model.entity.Category;
import com.edu.forum.application.model.request.UpdateCategoryRequest;
import com.edu.forum.common.mapper.BeanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateCategoryRequest2EntityMapper extends BeanMapper<UpdateCategoryRequest, Category> {
  UpdateCategoryRequest2EntityMapper INSTANCE = Mappers.getMapper(UpdateCategoryRequest2EntityMapper.class);
}
