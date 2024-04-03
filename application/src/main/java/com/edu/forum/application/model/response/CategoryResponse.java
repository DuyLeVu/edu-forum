package com.edu.forum.application.model.response;

import com.edu.forum.application.model.entity.Role;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryResponse {
  private Long id;
  private String name;
  private String description;
  private Role role;
  private Long countPost;
}
