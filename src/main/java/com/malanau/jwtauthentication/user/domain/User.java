package com.malanau.jwtauthentication.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class User {
  private final Login login;
  private final Password password;

  public boolean passwordMatches(final Password password) {
    return this.password.equals(password);
  }
}
