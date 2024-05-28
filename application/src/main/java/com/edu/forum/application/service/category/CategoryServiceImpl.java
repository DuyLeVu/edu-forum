package com.edu.forum.application.service.category;

import com.edu.forum.application.mapper.CreateCategoryRequest2EntityMapper;
import com.edu.forum.application.mapper.Entity2CategoryResponseMapper;
import com.edu.forum.application.mapper.Entity2CreateCategoryResponseMapper;
import com.edu.forum.application.mapper.UpdateCategoryRequest2EntityMapper;
import com.edu.forum.application.model.Role;
import com.edu.forum.application.model.entity.Category;
import com.edu.forum.application.model.filter.CategoryFilter;
import com.edu.forum.application.model.request.CreateCategoryRequest;
import com.edu.forum.application.model.request.UpdateCategoryRequest;
import com.edu.forum.application.model.response.CategoryResponse;
import com.edu.forum.application.model.response.CreateCategoryResponse;
import com.edu.forum.application.repository.CategoryRepository;
import com.edu.forum.application.service.RoleService;
import com.edu.forum.application.util.ErrorCode;
import com.edu.forum.application.util.Filters;
import com.edu.forum.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
  private final CategoryRepository categoryRepository;

  private final RoleService roleService;

//    @Autowired
//    private IPostService postService;

  @Override
  public Iterable<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Optional<Category> findById(Long id) {
    return categoryRepository.findById(id);
  }

  @Override
  public Category getById(Long id) {
    var category = categoryRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND, "Category with id: "
    + id + " not found"));
    return category;
  }

  @Override
  @Transactional
  public CategoryResponse update(UpdateCategoryRequest updateCategoryRequest) {
    var id = updateCategoryRequest.getId();
    var category = categoryRepository.findById(id)
        .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND, "Category with id: " + id + " not found"));
    UpdateCategoryRequest2EntityMapper.INSTANCE.mapTo(updateCategoryRequest, category);
    return Entity2CategoryResponseMapper.INSTANCE.map(categoryRepository.save(category));
  }

  @Override
  public Category updateCountPost(Category category) {
    var category1 = categoryRepository.findById(category.getId()).get();
    category1.setName(category.getName())
        .setRole(category.getRole())
        .setCountPost(category1.getCountPost() - 1)
        .setDescription(category.getDescription());
    return categoryRepository.save(category1);
  }

  @Override
  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public void remove(Long id) {
    categoryRepository.deleteById(id);
  }

  @Override
  public Iterable<Category> findTop7Category() {
    return categoryRepository.findTop7Category();
  }

  @Override
  public Page<Category> getAll(CategoryFilter filter, Pageable pageable) {
    int size = pageable.getPageSize();
    int page = pageable.getPageNumber();
    if (page >= 1) {
      page = page - 1;
    } else if (page < 0) {
      page = 0;
    }
    Pageable pageDefault = PageRequest.of(page, size);
    return categoryRepository.findAll(Filters.toSpecification(filter), pageDefault);
  }

  @Override
  public Iterable<Category> getAllNoPaging() {
    return categoryRepository.getCategoryForUser();
  }

  @Override
  public Long countPostByCategory(Long categoryId) {
//        return postService.countPostByCategoryId(categoryId);
    return null;
  }

//  @Override
//  public Page<Category> searchByCondition(CategoryFilter filter, Pageable pageable) {
//    return categoryRepository.findAll(Filters.toSpecification(filter), pageable);
//  }

  @Override
  public Page<CategoryResponse> searchByCondition(CategoryFilter filter, Pageable pageable) {
    return categoryRepository.findAll(Filters.toSpecification(filter), pageable).map(Entity2CategoryResponseMapper.INSTANCE::map);
  }

  @Override
  public CreateCategoryResponse save(CreateCategoryRequest request) {
    if (categoryRepository.findByName(request.getName().trim()).isPresent()) {
      throw new BusinessException(ErrorCode.CATEGORY_ALREADY_EXISTS, "Category with name: "
      + request.getName() + " already exists");
    }
    var category = CreateCategoryRequest2EntityMapper.INSTANCE.map(request);
    category.setRole(roleService.findByName(Role.ROLE_USER.name()));
    return Entity2CreateCategoryResponseMapper.INSTANCE.map(categoryRepository.save(category));
  }
}
