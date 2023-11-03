package com.malanau.jwtauthentication.auth.application.create;

import com.malanau.jwtauthentication.auth.domain.AuthenticationTokenProvider;
import com.malanau.jwtauthentication.user.domain.Login;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationTokenCreator {
  AuthenticationTokenProvider authenticationTokenProvider;

  public String createToken(final String login) {
    return authenticationTokenProvider.createToken(new Login(login)).value();
  }
}
