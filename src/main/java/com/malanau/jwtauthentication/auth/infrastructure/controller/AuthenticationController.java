package com.malanau.jwtauthentication.auth.infrastructure.controller;

import com.malanau.jwtauthentication.auth.application.authenticate.AuthRequest;
import com.malanau.jwtauthentication.auth.application.authenticate.AuthResponse;
import com.malanau.jwtauthentication.auth.application.authenticate.UserAuthenticator;
import com.malanau.jwtauthentication.auth.domain.AuthPassword;
import com.malanau.jwtauthentication.auth.domain.AuthUsername;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

  UserAuthenticator userAuthenticator;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody final AuthRequest authRequest) {
    final AuthResponse authResponse =
        userAuthenticator.authenticate(
            new AuthUsername(authRequest.getUsername()),
            new AuthPassword(authRequest.getPassword()));

    return ResponseEntity.ok(authResponse);
  }
}
