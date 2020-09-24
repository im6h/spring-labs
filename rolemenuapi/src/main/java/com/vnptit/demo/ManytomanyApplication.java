package com.vnptit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ManytomanyApplication {

  public static void main(String[] args) {
    SpringApplication.run(ManytomanyApplication.class, args);
  }

}
