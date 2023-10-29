package com.malanau.jwtauthentication.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.malanau.jwtauthentication.auth.application.UserAuthentication;
import com.malanau.jwtauthentication.middleware.JwtAuthMiddleware;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class BackendServerConfiguration {

  private final UserAuthentication userAuthentication;

  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    http.addFilterBefore(
            new JwtAuthMiddleware(userAuthentication), BasicAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
        .authorizeHttpRequests(
            (requests) ->
                requests
                    .requestMatchers(HttpMethod.POST, "/login", "/register")
                    .permitAll()
                    .anyRequest()
                    .authenticated());
    return http.build();
  }
}
