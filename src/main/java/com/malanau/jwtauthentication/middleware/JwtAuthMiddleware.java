package com.malanau.jwtauthentication.middleware;

import com.malanau.jwtauthentication.auth.application.authenticate.UserAuthenticator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@AllArgsConstructor
public class JwtAuthMiddleware extends OncePerRequestFilter {

  private final UserAuthenticator userAuthenticator;

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
          SecurityContextHolder.getContext()
              .setAuthentication(userAuthenticator.validateToken(authElements[1]));
        } catch (final RuntimeException e) {
          SecurityContextHolder.clearContext();
          throw e;
        }
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
