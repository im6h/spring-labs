package com.vnptit.demo.exception;

import java.sql.SQLIntegrityConstraintViolationException;

public class DuplicateException extends SQLIntegrityConstraintViolationException {
  public DuplicateException(String reason) {
    super(reason);
  }
}
