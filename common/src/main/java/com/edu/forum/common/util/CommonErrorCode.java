package com.edu.forum.common.util;

import com.edu.forum.common.exception.BusinessErrorCode;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;

//@Log4j2
public class CommonErrorCode {
  public static final BusinessErrorCode INTERNAL_SERVER_ERROR =
      new BusinessErrorCode(5000, "Internal server error", 500);
//  public static final BusinessErrorCode INTERNAL_SERVER_ERROR =
//      new BusinessErrorCode(5001, "Internal server error", 500);
  public static final BusinessErrorCode INTERNAL_DATABASE_ERROR =
      new BusinessErrorCode(5002, "Internal database error", 500);
  public static final BusinessErrorCode INVALID_PARAMETERS =
      new BusinessErrorCode(4000, "Invalid parameters", 400);
  public static final BusinessErrorCode UNAUTHORIZED =
      new BusinessErrorCode(4001, "You need to login to access this resource", 401);
  public static final BusinessErrorCode FORBIDDEN =
      new BusinessErrorCode(4002, "You don't have permission to access this resource", 403);
  public static final BusinessErrorCode ERROR_MESSAGE_NOT_FOUND =
      new BusinessErrorCode(4003, "Error message not found", 404);

  static {
    var codes = new HashSet<Integer>();
    var duplications = Arrays.stream(CommonErrorCode.class.getDeclaredFields())
        .filter(f -> Modifier.isStatic(f.getModifiers()) && f.getType().equals(BusinessErrorCode.class))
        .map(f -> {
          try {
            return ((BusinessErrorCode) f.get(null)).getCode();
          }
          catch (IllegalAccessException e) {
//            log.error("Can't load error code into map", e);
            System.out.println("Can't load error code into map" + e);
            throw new IllegalArgumentException(e);
          }
        })
        .filter(c -> !codes.add(c))
        .toList();
    if (!duplications.isEmpty()) {
      throw new IllegalArgumentException("Found error code duplication: " + duplications);
    }
  }

  private CommonErrorCode() {
    throw new UnsupportedOperationException();
  }
}
