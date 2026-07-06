package com.volane.employee_crud.security.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.volane.employee_crud.security.handler.JwtAccessDeniedHandler;
import com.volane.employee_crud.security.handler.JwtAuthenticationEntryPoint;
import com.volane.employee_crud.security.jwt.JwtAuthenticationFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityConfig.class, AuthenticationConfiguration.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class SecurityConfigDiffblueTest {
  @Autowired private AuthenticationConfiguration authenticationConfiguration;

  @MockBean private AuthenticationProvider authenticationProvider;

  @MockBean private JwtAccessDeniedHandler jwtAccessDeniedHandler;

  @MockBean private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @MockBean private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired private SecurityConfig securityConfig;

  /**
   * Test {@link SecurityConfig#authenticationManager(AuthenticationConfiguration)}.
   *
   * <ul>
   *   <li>Then return {@link ProviderManager}.
   * </ul>
   *
   * <p>Method under test: {@link SecurityConfig#authenticationManager(AuthenticationConfiguration)}
   */
  @Test
  @DisplayName(
      "Test authenticationManager(AuthenticationConfiguration); then return ProviderManager")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthenticationManager SecurityConfig.authenticationManager(AuthenticationConfiguration)"
  })
  void testAuthenticationManager_thenReturnProviderManager() throws Exception {
    // Arrange and Act
    AuthenticationManager actualAuthenticationManagerResult =
        securityConfig.authenticationManager(authenticationConfiguration);

    // Assert
    assertTrue(actualAuthenticationManagerResult instanceof ProviderManager);
    assertEquals(1, ((ProviderManager) actualAuthenticationManagerResult).getProviders().size());
    assertTrue(
        ((ProviderManager) actualAuthenticationManagerResult)
            .isEraseCredentialsAfterAuthentication());
  }
}
