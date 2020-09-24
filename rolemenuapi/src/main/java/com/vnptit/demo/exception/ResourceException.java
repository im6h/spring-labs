package com.vnptit.demo.exception;

import com.sun.media.sound.InvalidFormatException;
import com.vnptit.demo.response.MessageResponse;
import com.vnptit.demo.util.Status;
import com.vnptit.demo.util.StatusCode;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.naming.CommunicationException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


@RestControllerAdvice
public class ResourceException {

  @ExceptionHandler({ConstraintViolationException.class, RollbackException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public MessageResponse<?> handleValidateResource(Exception ex) {
    return new MessageResponse<>(
            null,
            Status.FAILED,
            StatusCode.ERR_100,
            ex.getMessage()
    );
  }

  @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public MessageResponse<?> handleNotFoundResource(Exception ex, WebRequest request) {
    return new MessageResponse<>(
            null,
            Status.FAILED,
            StatusCode.NF_404,
            ex.getMessage()
    );
  }

  @ExceptionHandler({DataIntegrityViolationException.class, DuplicateException.class, SQLIntegrityConstraintViolationException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public MessageResponse<?> handleDuplicateName(DuplicateException ex, WebRequest request) {
    return new MessageResponse<>(
            null,
            Status.FAILED,
            StatusCode.ERR_101,
            ex.getMessage()
    );
  }

  @ExceptionHandler({IllegalStateException.class, ConnectException.class, JDBCConnectionException.class, CommunicationException.class, SQLException.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public MessageResponse<?> handleConnectDatabase(Exception ex, WebRequest request) {
    return new MessageResponse<>(
            null,
            Status.ERROR,
            StatusCode.ERR_500,
            ex.getMessage()
    );
  }

  @ExceptionHandler({HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public MessageResponse<?> handleRequestBodyNull(Exception ex, WebRequest request) {
    return new MessageResponse<>(
            null,
            Status.ERROR,
            StatusCode.ERR_001,
            ex.getMessage()
    );
  }

  @ExceptionHandler({NumberFormatException.class,InvalidFormatException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public MessageResponse<?> handleUrlInvalid(Exception ex, WebRequest request) {
    return new MessageResponse<>(
            null,
            Status.ERROR,
            StatusCode.ERR_099,
            ex.getMessage()
    );
  }


  @ExceptionHandler({NullPointerException.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public MessageResponse<?> exception(NullPointerException ex, WebRequest request) {
    return new MessageResponse<>(
            null,
            Status.ERROR,
            StatusCode.ERR_103,
            ex.getLocalizedMessage()
    );
  }

  @ExceptionHandler({ValidateException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public MessageResponse<?> validateException(ValidateException ex, WebRequest request) {
    return new MessageResponse<>(
            null,
            Status.ERROR,
            StatusCode.ERR_103,
            ex.getMessage()
    );
  }

}