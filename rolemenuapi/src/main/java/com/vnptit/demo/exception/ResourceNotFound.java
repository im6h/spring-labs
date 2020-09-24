package com.vnptit.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class ResourceNotFound {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorMessage notFoundMenuWithId(NotFoundException ex) {
    return new ErrorMessage(ex.getMessage());
  }

  @ExceptionHandler(ValidateException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorMessage createMenuFailed(ValidateException ex){
    return new ErrorMessage(ex.getMessage());
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage nullException(NullPointerException ex, WebRequest request){
    return new ErrorMessage("Role id invalid");
  }
}