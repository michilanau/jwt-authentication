package com.malanau.jwtauthentication.user.infrastructure.controller;

import com.malanau.jwtauthentication.shared.application.ErrorResponse;
import com.malanau.jwtauthentication.user.application.UserResponse;
import com.malanau.jwtauthentication.user.application.authenticate.UserAuthRequest;
import com.malanau.jwtauthentication.user.application.authenticate.UserAuthenticator;
import com.malanau.jwtauthentication.user.application.register.UserRegister;
import com.malanau.jwtauthentication.user.application.register.UserRegisterRequest;
import com.malanau.jwtauthentication.user.domain.InvalidAuthLogin;
import com.malanau.jwtauthentication.user.domain.InvalidAuthPassword;
import com.malanau.jwtauthentication.user.domain.UserAlreadyExists;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<?> login(@RequestBody final UserAuthRequest userAuthRequest) {
    try {
      final UserResponse userResponse = userAuthenticator.authenticate(userAuthRequest);
      return ResponseEntity.ok(userResponse);
    } catch (final InvalidAuthLogin | InvalidAuthPassword e) {
      final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
      final ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), e.getMessage());
      return ResponseEntity.status(httpStatus).body(errorResponse);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody final UserRegisterRequest userRegisterRequest) {
    try {
      final UserResponse userResponse = userRegister.register(userRegisterRequest);
      return ResponseEntity.ok(userResponse);
    } catch (final UserAlreadyExists e) {
      final HttpStatus httpStatus = HttpStatus.CONFLICT;
      final ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), e.getMessage());
      return ResponseEntity.status(httpStatus).body(errorResponse);
    }
  }
}
