package com.lab.restsecurity.payload;

public class RandomStuff {
  private String message;


  public RandomStuff(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
