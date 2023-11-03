package com.malanau.jwtauthentication.auth.domain;

import com.malanau.jwtauthentication.user.domain.Login;

public interface AuthenticationTokenProvider {

  Token createToken(final Login login);

  Login validateToken(final Token token);
}
