package com.edu.forum.application.controller;

import com.edu.forum.application.model.entity.Category;
import com.edu.forum.application.model.filter.CategoryFilter;
import com.edu.forum.application.model.request.CreateCategoryRequest;
import com.edu.forum.application.model.request.UpdateCategoryRequest;
import com.edu.forum.application.model.response.CategoryResponse;
import com.edu.forum.application.model.response.CreateCategoryResponse;
import com.edu.forum.application.service.category.ICategoryService;
import com.edu.forum.common.model.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@Validated
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public ResponseEntity<Page<Category>> getAll(CategoryFilter filter, Pageable pageable) {
            Page<Category> categories = categoryService.getAll(filter, pageable);
        if (categories == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/categories")
    public ResponseEntity<Iterable<Category>> getAllNoPaging() {
        Iterable<Category> categories = categoryService.getAllNoPaging();
        if (categories == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/top7Category")
    public ResponseEntity<Iterable<Category>> getTop7Category() {
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<CreateCategoryResponse> save(@Valid @RequestBody CreateCategoryRequest request) {
        return new ResponseEntity<>(categoryService.save(request), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/countPostByCategory/{id}")
    public ResponseEntity<Long> countPostByCategory(@PathVariable Long id) {
        Long postByCategory = categoryService.countPostByCategory(id);
        return new ResponseEntity<>(postByCategory, HttpStatus.OK);
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/search")
//    public ResponseEntity<Page<Category>> searchByCondition(CategoryFilter filter, Pageable pageable) {
//        Page<Category> categories = categoryService.searchByCondition(filter, pageable);
//        if (categories == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(categories, HttpStatus.OK);
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/search")
    public ResponseEntity<Page<CategoryResponse>> searchByCondition(CategoryFilter filter, Pageable pageable) {
        Page<CategoryResponse> categories = categoryService.searchByCondition(filter, pageable);
        if (categories == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public Response<CategoryResponse> update(@Positive @PathVariable Long id,
                                             @RequestBody UpdateCategoryRequest request) {
        return Response.ofSucceeded(categoryService.update(request.setId(id)));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public Response<Void> delete(@Positive @PathVariable Long id) {
        categoryService.remove(id);
        return Response.ofSucceeded();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/admin/noti")
    public ResponseEntity<Iterable<Category>> getForAdmin() {
        Iterable<Category> categories = categoryService.findAll();
        if (categories == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
