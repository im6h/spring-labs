package com.spring.securtity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(name = "/")
public class HomeController {

  @GetMapping("")
  public String firstPage() {
    return new String("Hello World, Spring boot lab");
  }
}
