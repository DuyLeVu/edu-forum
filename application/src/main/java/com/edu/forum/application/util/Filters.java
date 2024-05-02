package com.edu.forum.application.util;

import com.edu.forum.application.model.entity.*;
import com.edu.forum.application.model.filter.CategoryFilter;
import com.edu.forum.application.model.filter.PostFilter;
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

  public static Specification<Post> toSpecification(PostFilter filter) {
    return Specification.where(contain(Post_.title, filter.getTitle()))
        .or(Specifications.equalsJoin(Post_.user, User.class, User_.username, filter.getUserName()));
  }
}
