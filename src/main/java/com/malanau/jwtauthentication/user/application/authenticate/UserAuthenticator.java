package com.malanau.jwtauthentication.user.application.authenticate;

import com.malanau.jwtauthentication.auth.application.create.AuthenticationTokenCreator;
import com.malanau.jwtauthentication.user.application.UserResponse;
import com.malanau.jwtauthentication.user.domain.InvalidAuthLogin;
import com.malanau.jwtauthentication.user.domain.InvalidAuthPassword;
import com.malanau.jwtauthentication.user.domain.Login;
import com.malanau.jwtauthentication.user.domain.Password;
import com.malanau.jwtauthentication.user.domain.User;
import com.malanau.jwtauthentication.user.domain.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserAuthenticator {

  private final UserRepository userRepository;
  private final AuthenticationTokenCreator authenticationTokenCreator;

  public UserResponse authenticate(final UserAuthRequest userAuthRequest) {

    final Optional<User> user = userRepository.search(new Login(userAuthRequest.getLogin()));

    ensureUserExist(user, new Login(userAuthRequest.getLogin()));
    ensureCredentialsAreValid(user.get(), new Password(userAuthRequest.getPassword()));

    return new UserResponse(
        user.get().getLogin().value(),
        authenticationTokenCreator.createToken(user.get().getLogin().value()));
  }

  private void ensureUserExist(final Optional<User> user, final Login login) {
    if (!user.isPresent()) {
      throw new InvalidAuthLogin(login);
    }
  }

  private void ensureCredentialsAreValid(final User user, final Password password) {
    if (!user.passwordMatches(password)) {
      throw new InvalidAuthPassword(user.getLogin());
    }
  }
}
