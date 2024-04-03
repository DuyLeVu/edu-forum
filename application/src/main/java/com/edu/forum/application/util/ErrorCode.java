package com.edu.forum.application.util;

import com.edu.forum.common.exception.BusinessErrorCode;

public class ErrorCode {
  public static final BusinessErrorCode CATEGORY_ALREADY_EXISTS = new BusinessErrorCode(1000, "Category already exist", 400);
  public static final BusinessErrorCode CATEGORY_NOT_FOUND = new BusinessErrorCode(1001, "Category not found", 404);

  private ErrorCode() {
    throw new UnsupportedOperationException();
  }
}
