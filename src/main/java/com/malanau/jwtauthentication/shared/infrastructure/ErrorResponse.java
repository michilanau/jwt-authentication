package com.malanau.jwtauthentication.shared.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
  Integer code;
  String message;
}
