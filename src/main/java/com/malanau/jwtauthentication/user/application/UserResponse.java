package com.malanau.jwtauthentication.user.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
  String login;
  String token;
}
