package com.lab.restsecurity;

import com.lab.restsecurity.user.User;
import com.lab.restsecurity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RestsecurityApplication implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(RestsecurityApplication.class, args);
  }

  @Autowired
  UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {

    User user = new User();
    user.setUsername("loda");
    user.setPassword(passwordEncoder.encode("loda"));
    userRepository.save(user);
    System.out.println(user);
  }
}
