package com.malanau.jwtauthentication.user.application.authenticate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserAuthRequest {
  String login;
  String password;
}
