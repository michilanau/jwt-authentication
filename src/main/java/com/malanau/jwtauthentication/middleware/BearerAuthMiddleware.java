package com.malanau.jwtauthentication.middleware;

import com.malanau.jwtauthentication.auth.application.validate.AuthenticationTokenValidator;
import com.malanau.jwtauthentication.user.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@AllArgsConstructor
public class BearerAuthMiddleware extends OncePerRequestFilter {

  private final AuthenticationTokenValidator authenticationTokenValidator;

  @Override
  protected void doFilterInternal(
      final HttpServletRequest httpServletRequest,
      final HttpServletResponse httpServletResponse,
      final FilterChain filterChain)
      throws ServletException, IOException {
    final String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

    if (header != null) {
      final String[] authElements = header.split(" ");

      if (authElements.length == 2 && "Bearer".equals(authElements[0])) {
        try {
          final User user = authenticationTokenValidator.validateToken(authElements[1]);
          SecurityContextHolder.getContext()
              .setAuthentication(
                  new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()));
        } catch (final RuntimeException e) {
          SecurityContextHolder.clearContext();
          throw e;
        }
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
