package com.malanau.jwtauthentication.health_check.infrastructure.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class HealthCheckController {

  @GetMapping("/health-check")
  public Map<String, String> healthCheckGet() {
    final HashMap<String, String> status = new HashMap<>();

    status.put("application", "jwt-authentication");
    status.put("status", "ok");

    return status;
  }
}
