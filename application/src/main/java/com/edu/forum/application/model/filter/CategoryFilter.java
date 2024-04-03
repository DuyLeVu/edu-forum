package com.edu.forum.application.model.filter;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryFilter {
  private String search;
}
