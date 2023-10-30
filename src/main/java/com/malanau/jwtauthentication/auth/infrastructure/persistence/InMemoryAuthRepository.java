package com.malanau.jwtauthentication.auth.infrastructure.persistence;

import com.malanau.jwtauthentication.auth.domain.AuthPassword;
import com.malanau.jwtauthentication.auth.domain.AuthRepository;
import com.malanau.jwtauthentication.auth.domain.AuthUser;
import com.malanau.jwtauthentication.auth.domain.AuthUsername;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public final class InMemoryAuthRepository implements AuthRepository {
  private final HashMap<AuthUsername, AuthPassword> users =
      new HashMap<>() {
        {
          put(
              new AuthUsername("michi"),
              new AuthPassword("a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3"));
        }
      };

  @Override
  public Optional<AuthUser> search(final AuthUsername username) {
    return users.containsKey(username)
        ? Optional.of(new AuthUser(username, users.get(username)))
        : Optional.empty();
  }
}
