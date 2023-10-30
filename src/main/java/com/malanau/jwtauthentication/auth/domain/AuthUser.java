package com.malanau.jwtauthentication.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class AuthUser {
  private final AuthUsername username;
  private final AuthPassword password;

  public boolean passwordMatches(final AuthPassword password) {
    return this.password.equals(password);
  }
}
