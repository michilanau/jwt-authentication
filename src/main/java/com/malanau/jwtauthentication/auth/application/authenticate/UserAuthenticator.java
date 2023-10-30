package com.malanau.jwtauthentication.auth.application.authenticate;

import com.malanau.jwtauthentication.auth.domain.AuthPassword;
import com.malanau.jwtauthentication.auth.domain.AuthRepository;
import com.malanau.jwtauthentication.auth.domain.AuthUser;
import com.malanau.jwtauthentication.auth.domain.AuthUsername;
import com.malanau.jwtauthentication.auth.domain.AuthenticationTokenProvider;
import java.util.Collections;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthenticator {

  private final AuthRepository authRepository;
  private final AuthenticationTokenProvider authenticationTokenProvider;

  public Authentication validateToken(final String token) {

    final String username = authenticationTokenProvider.validateToken(token);

    final Optional<AuthUser> user = authRepository.search(new AuthUsername(username));

    return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
  }

  public AuthResponse authenticate(final AuthUsername username, final AuthPassword password) {
    final Optional<AuthUser> auth = authRepository.search(username);

    ensureUserExist(auth, username);
    ensureCredentialsAreValid(auth.get(), password);

    return new AuthResponse(
        auth.get().getUsername().value(),
        authenticationTokenProvider.createToken(
            new AuthUsername(auth.get().getUsername().value())));
  }

  private void ensureUserExist(final Optional<AuthUser> auth, final AuthUsername username) {
    if (!auth.isPresent()) {
      // throw new InvalidAuthUsername(username);
    }
  }

  private void ensureCredentialsAreValid(final AuthUser auth, final AuthPassword password) {
    if (!auth.passwordMatches(password)) {
      // throw new InvalidAuthCredentials(auth.username());
    }
  }
}
