package com.malanau.jwtauthentication.shared.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
  Integer code;
  String message;
}
