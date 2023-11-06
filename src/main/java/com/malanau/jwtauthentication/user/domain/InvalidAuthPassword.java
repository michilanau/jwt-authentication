package com.malanau.jwtauthentication.user.domain;

public final class InvalidAuthPassword extends RuntimeException {
  public InvalidAuthPassword(final Login login) {
    super(String.format("The credentials for <%s> are invalid", login.value()));
  }
}
