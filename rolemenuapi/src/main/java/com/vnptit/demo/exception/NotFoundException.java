package com.vnptit.demo.exception;

import javax.persistence.EntityNotFoundException;

public class NotFoundException extends EntityNotFoundException {
  public NotFoundException(String e){
    super(e);
  }
}
