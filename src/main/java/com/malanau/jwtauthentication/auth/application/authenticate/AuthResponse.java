package com.malanau.jwtauthentication.auth.application.authenticate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthResponse {
  String username;
  String token;
}
