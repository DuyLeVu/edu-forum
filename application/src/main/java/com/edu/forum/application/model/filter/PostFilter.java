package com.edu.forum.application.model.filter;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostFilter {
  private String title;
  private String category;
  private String userName;
}
