package com.lab.restsecurity.controller;

import com.lab.restsecurity.jwt.JwtTokenProvider;
import com.lab.restsecurity.payload.LoginRequest;
import com.lab.restsecurity.payload.LoginResponse;
import com.lab.restsecurity.payload.RandomStuff;
import com.lab.restsecurity.user.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @PostMapping("/login")
  public LoginResponse authenticateUser( @RequestBody LoginRequest loginRequest) {

    // Xác thực thông tin người dùng Request lên
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            )
    );

    // Nếu không xảy ra exception tức là thông tin hợp lệ
    // Set thông tin authentication vào Security Context
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Trả về jwt cho người dùng.
    String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
    return new LoginResponse(jwt);
  }

  // Api /api/random yêu cầu phải xác thực mới có thể request
  @GetMapping("/random")
  public RandomStuff randomStuff(){
    return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
  }
}
