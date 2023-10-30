package com.malanau.jwtauthentication.auth.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.malanau.jwtauthentication.auth.domain.AuthUsername;
import com.malanau.jwtauthentication.auth.domain.AuthenticationTokenProvider;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider implements AuthenticationTokenProvider {

  @Value("${security.jwt.token.secret-key:default-secret-key}")
  private String secretKey;

  @Override
  public String createToken(final AuthUsername authUsername) {
    final Date now = new Date();
    final Date validity = new Date(now.getTime() + 3600000); // 1 hour

    final Algorithm algorithm = Algorithm.HMAC256(secretKey);
    return JWT.create()
        .withSubject(authUsername.value())
        .withIssuedAt(now)
        .withExpiresAt(validity)
        .sign(algorithm);
  }

  @Override
  public String validateToken(final String token) {

    final Algorithm algorithm = Algorithm.HMAC256(secretKey);

    final JWTVerifier verifier = JWT.require(algorithm).build();

    final DecodedJWT decoded = verifier.verify(token);

    return decoded.getSubject();
  }
}
