package com.malanau.jwtauthentication.auth.domain;

public interface AuthenticationTokenProvider {

  String createToken(final AuthUsername authUsername);

  String validateToken(final String token);
}
