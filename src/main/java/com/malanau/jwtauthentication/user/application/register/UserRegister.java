package com.malanau.jwtauthentication.user.application.register;

import com.malanau.jwtauthentication.auth.application.create.AuthenticationTokenCreator;
import com.malanau.jwtauthentication.user.application.UserResponse;
import com.malanau.jwtauthentication.user.domain.Login;
import com.malanau.jwtauthentication.user.domain.Password;
import com.malanau.jwtauthentication.user.domain.User;
import com.malanau.jwtauthentication.user.domain.UserAlreadyExists;
import com.malanau.jwtauthentication.user.domain.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegister {
  UserRepository userRepository;
  AuthenticationTokenCreator authenticationTokenCreator;

  public UserResponse register(final UserRegisterRequest userRegisterRequest) {

    final Optional<User> user = userRepository.search(new Login(userRegisterRequest.getLogin()));

    ensureUserDoesNotExist(user, new Login(userRegisterRequest.getLogin()));

    final User newUser =
        new User(
            new Login(userRegisterRequest.getLogin()),
            new Password(userRegisterRequest.getPassword()));
    userRepository.save(newUser);

    return new UserResponse(
        newUser.getLogin().value(),
        authenticationTokenCreator.createToken(newUser.getLogin().value()));
  }

  public void ensureUserDoesNotExist(final Optional<User> user, final Login login) {
    if (user.isPresent()) {
      throw new UserAlreadyExists(login);
    }
  }
}
