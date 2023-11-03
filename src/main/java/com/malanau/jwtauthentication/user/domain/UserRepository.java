package com.malanau.jwtauthentication.user.domain;

import java.util.Optional;

public interface UserRepository {
  Optional<User> search(Login login);

  void save(User user);
}
