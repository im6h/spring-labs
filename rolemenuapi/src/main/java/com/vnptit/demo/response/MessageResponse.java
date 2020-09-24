package com.vnptit.demo.response;

import com.vnptit.demo.util.Status;
import com.vnptit.demo.util.StatusCode;

public class MessageResponse<T> {
  private Status status;
  private StatusCode code;
  private String message;
  private T data;

  public MessageResponse() {
  }

  public MessageResponse(T data,Status status, StatusCode code, String message) {
    this.data = data;
    this.status = status;
    this.code = code;
    this.message = message;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public StatusCode getCode() {
    return code;
  }

  public void setCode(StatusCode code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
