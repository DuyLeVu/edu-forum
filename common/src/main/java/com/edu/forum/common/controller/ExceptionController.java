package com.edu.forum.common.controller;

import com.edu.forum.common.exception.*;
import com.edu.forum.common.model.response.Response;
import com.edu.forum.common.util.CommonErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
  private final ObjectMapper  objectMapper;

  @ExceptionHandler(BusinessException.class)
  protected void handleBusinessException(BusinessException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    handle(e, e.getErrorCode(), request, response);
  }

  @ExceptionHandler(BindException.class)
  protected void handleBindException(BindException e, HttpServletRequest request,
                                     HttpServletResponse response) throws IOException {
    var fieldViolations = e.getBindingResult().getAllErrors().stream()
        .map(error -> new FieldViolation(((FieldError) error).getField(), error.getDefaultMessage()))
        .toList();
    handleInvalidParams(e, fieldViolations, request, response);
  }

  private void handleInvalidParams(BindException e, List<FieldViolation> fieldViolations, HttpServletRequest request, HttpServletResponse response) throws IOException {
    var errorResponse = Response.ofFailed(CommonErrorCode.INVALID_PARAMETERS, e.getMessage(), fieldViolations);
    writeResponse(request, response, CommonErrorCode.INVALID_PARAMETERS.getHttpStatus(), errorResponse, e);
  }

  private void handle(Throwable t, BusinessErrorCode errorCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
    var errorResponse = Response.ofFailed(errorCode, t.getMessage());
    writeResponse(request, response, errorCode.getHttpStatus(), errorResponse, t);
  }

  private void writeResponse(HttpServletRequest request, HttpServletResponse servletResponse, int httpStatus, Response<?> errorResponse, Throwable t) throws IOException {
    servletResponse.setStatus(httpStatus);
    servletResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.JSON_UTF_8.type());
    byte[] body = objectMapper.writeValueAsBytes(errorResponse);
    servletResponse.setContentLength(body.length);
    servletResponse.getOutputStream().write(body);
  }
  @ExceptionHandler({AppException.class, InputInvalidException.class})  // Có thể bắt nhiều loại exception
  public ResponseEntity<String> handleCustomException(AppException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler({PostNotFoundException.class})  // Có thể bắt nhiều loại exception
  public ResponseEntity<String> handlePostNotFoundException(AppException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleUnwantedException(Exception e) {
    // Log lỗi ra và ẩn đi message thực sự (xem phần 3.2)
    e.printStackTrace();  // Thực tế người ta dùng logger
    return ResponseEntity.status(500).body("Unknow error");
  }
}
