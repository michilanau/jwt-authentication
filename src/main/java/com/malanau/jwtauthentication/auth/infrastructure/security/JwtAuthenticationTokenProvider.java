package com.malanau.jwtauthentication.auth.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.malanau.jwtauthentication.auth.domain.AuthenticationTokenProvider;
import com.malanau.jwtauthentication.auth.domain.Token;
import com.malanau.jwtauthentication.user.domain.Login;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationTokenProvider implements AuthenticationTokenProvider {

  @Value("${security.jwt.token.secret-key:default-secret-key}")
  private String secretKey;

  public void JwtTokenProvider(final String secretKey) {
    // this is to avoid having the raw secret key available in the JVM
    this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  @Override
  public Token createToken(final Login login) {
    final Date now = new Date();
    final Date validity = new Date(now.getTime() + 3600000); // 1 hour

    final Algorithm algorithm = Algorithm.HMAC256(secretKey);
    return new Token(
        JWT.create()
            .withSubject(login.value())
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .sign(algorithm));
  }

  @Override
  public Login validateToken(final Token token) {

    final Algorithm algorithm = Algorithm.HMAC256(secretKey);

    final JWTVerifier verifier = JWT.require(algorithm).build();

    final DecodedJWT decoded = verifier.verify(token.value());

    return new Login(decoded.getSubject());
  }
}
