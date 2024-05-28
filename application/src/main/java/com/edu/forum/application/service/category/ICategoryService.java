package com.edu.forum.application.service.category;

import com.edu.forum.application.model.entity.Category;
import com.edu.forum.application.model.filter.CategoryFilter;
import com.edu.forum.application.model.request.CreateCategoryRequest;
import com.edu.forum.application.model.request.UpdateCategoryRequest;
import com.edu.forum.application.model.response.CategoryResponse;
import com.edu.forum.application.model.response.CreateCategoryResponse;
import com.edu.forum.application.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ICategoryService extends IGeneralService<Category> {
    Category updateCountPost(Category category);

    Iterable<Category> findTop7Category();

    Page<Category> getAll(CategoryFilter filter, Pageable pageable);

    Iterable<Category> getAllNoPaging();

    Long countPostByCategory(Long categoryId);

//    Page<Category> searchByCondition(CategoryFilter filter, Pageable pageable);

    Page<CategoryResponse> searchByCondition(CategoryFilter filter, Pageable pageable);

    CreateCategoryResponse save(CreateCategoryRequest request);

    Category getById(Long id);

    CategoryResponse update(UpdateCategoryRequest updateCategoryRequest);
}
