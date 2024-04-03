package com.edu.forum.application.util;

import com.edu.forum.application.model.entity.Category;
import com.edu.forum.application.model.entity.Category_;
import com.edu.forum.application.model.filter.CategoryFilter;
import org.springframework.data.jpa.domain.Specification;

import static com.edu.forum.application.util.Specifications.contain;

public class Filters {
  private Filters() {
    throw new UnsupportedOperationException();
  }

  public static Specification<Category> toSpecification(CategoryFilter filter) {
    return Specification.where(contain(Category_.name, filter.getSearch()))
        .or(contain(Category_.description, filter.getSearch()));
  }
}
