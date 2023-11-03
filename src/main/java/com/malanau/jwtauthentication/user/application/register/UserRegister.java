package com.malanau.jwtauthentication.user.application.register;

import com.malanau.jwtauthentication.auth.application.create.AuthenticationTokenCreator;
import com.malanau.jwtauthentication.user.application.UserResponse;
import com.malanau.jwtauthentication.user.domain.Login;
import com.malanau.jwtauthentication.user.domain.Password;
import com.malanau.jwtauthentication.user.domain.User;
import com.malanau.jwtauthentication.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegister {
  UserRepository userRepository;
  AuthenticationTokenCreator authenticationTokenCreator;

  public UserResponse register(final UserRegisterRequest userRegisterRequest) {

    final User user =
        new User(
            new Login(userRegisterRequest.getLogin()),
            new Password(userRegisterRequest.getPassword()));
    userRepository.save(user);

    return new UserResponse(
        user.getLogin().value(), authenticationTokenCreator.createToken(user.getLogin().value()));
  }
}
