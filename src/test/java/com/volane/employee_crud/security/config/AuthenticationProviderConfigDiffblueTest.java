package com.volane.employee_crud.security.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.volane.employee_crud.security.service.AdminUserDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthenticationProviderConfigDiffblueTest {
  /**
   * Test {@link AuthenticationProviderConfig#passwordEncoder()}.
   *
   * <p>Method under test: {@link AuthenticationProviderConfig#passwordEncoder()}
   */
  @Test
  @DisplayName("Test passwordEncoder()")
  @Tag("MaintainedByDiffblue")
  void testPasswordEncoder() {
    // Arrange, Act and Assert
    assertTrue(
        new AuthenticationProviderConfig().passwordEncoder() instanceof BCryptPasswordEncoder);
  }

  /**
   * Test {@link AuthenticationProviderConfig#authenticationProvider(UserDetailsService,
   * PasswordEncoder)}.
   *
   * <ul>
   *   <li>Given {@link AuthenticationProviderConfig} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link
   * AuthenticationProviderConfig#authenticationProvider(UserDetailsService, PasswordEncoder)}
   */
  @Test
  @DisplayName(
      "Test authenticationProvider(UserDetailsService, PasswordEncoder); given AuthenticationProviderConfig (default constructor)")
  @Tag("MaintainedByDiffblue")
  void testAuthenticationProvider_givenAuthenticationProviderConfig() {
    // Arrange
    AuthenticationProviderConfig authenticationProviderConfig = new AuthenticationProviderConfig();
    AdminUserDetailsService userDetailsService =
        new AdminUserDetailsService(new BCryptPasswordEncoder(), "janedoe", "iloveyou");

    // Act
    AuthenticationProvider actualAuthenticationProviderResult =
        authenticationProviderConfig.authenticationProvider(
            userDetailsService, new BCryptPasswordEncoder());

    // Assert
    assertTrue(actualAuthenticationProviderResult instanceof DaoAuthenticationProvider);
    assertTrue(
        ((DaoAuthenticationProvider) actualAuthenticationProviderResult).getUserCache()
            instanceof NullUserCache);
    assertFalse(
        ((DaoAuthenticationProvider) actualAuthenticationProviderResult)
            .isForcePrincipalAsString());
    assertTrue(
        ((DaoAuthenticationProvider) actualAuthenticationProviderResult)
            .isHideUserNotFoundExceptions());
  }
}
