package com.malanau.jwtauthentication.user.application.register;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRegisterRequest {
  String login;
  String password;
}
