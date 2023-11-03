package com.malanau.jwtauthentication.user.domain;

public final class InvalidAuthLogin extends RuntimeException {
  public InvalidAuthLogin(final Login login) {
    super(String.format("The user <%s> does not exist", login.value()));
  }
}
