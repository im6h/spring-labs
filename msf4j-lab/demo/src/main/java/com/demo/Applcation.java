package com.demo;

import com.demo.controller.UserController;
import com.demo.dao.UserRepository;
import org.wso2.msf4j.MicroservicesRunner;

import javax.persistence.Persistence;

public class Applcation {
  public static void main(String[] args) {
    new MicroservicesRunner()
            .deploy(new UserController(getUserRepository()))
            .start();
  }

  public static UserRepository getUserRepository() {

    return new UserRepository(Persistence.createEntityManagerFactory("jpa"));
  }
}
