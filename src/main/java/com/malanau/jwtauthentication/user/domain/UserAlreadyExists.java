package com.malanau.jwtauthentication.user.domain;

public final class UserAlreadyExists extends RuntimeException {
  public UserAlreadyExists(final Login login) {
    super(String.format("The user <%s> already exists", login.value()));
  }
}
