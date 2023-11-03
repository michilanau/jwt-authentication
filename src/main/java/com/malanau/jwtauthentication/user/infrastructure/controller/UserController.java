package com.malanau.jwtauthentication.user.infrastructure.controller;

import com.malanau.jwtauthentication.user.application.UserResponse;
import com.malanau.jwtauthentication.user.application.authenticate.UserAuthRequest;
import com.malanau.jwtauthentication.user.application.authenticate.UserAuthenticator;
import com.malanau.jwtauthentication.user.application.register.UserRegister;
import com.malanau.jwtauthentication.user.application.register.UserRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

  UserAuthenticator userAuthenticator;
  UserRegister userRegister;

  @PostMapping("/login")
  public ResponseEntity<UserResponse> login(@RequestBody final UserAuthRequest userAuthRequest) {
    final UserResponse userResponse = userAuthenticator.authenticate(userAuthRequest);

    return ResponseEntity.ok(userResponse);
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(
      @RequestBody final UserRegisterRequest userRegisterRequest) {

    final UserResponse userResponse = userRegister.register(userRegisterRequest);

    return ResponseEntity.ok(userResponse);
  }
}
