package com.malanau.jwtauthentication.user.infrastructure.persistence;

import com.malanau.jwtauthentication.user.domain.Login;
import com.malanau.jwtauthentication.user.domain.Password;
import com.malanau.jwtauthentication.user.domain.User;
import com.malanau.jwtauthentication.user.domain.UserRepository;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public final class InMemoryUserRepository implements UserRepository {
  private final HashMap<Login, Password> users =
      new HashMap<>() {
        {
          put(
              new Login("michi"),
              new Password("a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3"));
        }
      };

  @Override
  public Optional<User> search(final Login login) {
    return users.containsKey(login)
        ? Optional.of(new User(login, users.get(login)))
        : Optional.empty();
  }

  @Override
  public void save(final User user) {
    users.put(user.getLogin(), user.getPassword());
  }
}
