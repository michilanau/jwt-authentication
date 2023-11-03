package com.malanau.jwtauthentication.auth.application.validate;

import com.malanau.jwtauthentication.auth.domain.AuthenticationTokenProvider;
import com.malanau.jwtauthentication.auth.domain.Token;
import com.malanau.jwtauthentication.user.domain.Login;
import com.malanau.jwtauthentication.user.domain.User;
import com.malanau.jwtauthentication.user.domain.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationTokenValidator {

  AuthenticationTokenProvider authenticationTokenProvider;
  UserRepository userRepository;

  public User validateToken(final String token) {

    final Login login = authenticationTokenProvider.validateToken(new Token(token));

    final Optional<User> user = userRepository.search(login);

    return user.get();
  }
}
