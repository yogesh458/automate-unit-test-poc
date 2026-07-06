package com.volane.employee_crud.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.volane.employee_crud.dto.auth.LoginRequest;
import com.volane.employee_crud.exception.GlobalExceptionHandler;
import com.volane.employee_crud.security.handler.SecurityExceptionHandler;
import com.volane.employee_crud.security.jwt.JwtService;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(
    classes = {AuthController.class, GlobalExceptionHandler.class, SecurityExceptionHandler.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class AuthControllerDiffblueTest {
  @Autowired private AuthController authController;

  @MockBean private AuthenticationManager authenticationManager;

  @Autowired private GlobalExceptionHandler globalExceptionHandler;

  @MockBean private JwtService jwtService;

  @Autowired private SecurityExceptionHandler securityExceptionHandler;

  /**
   * Test {@link AuthController#login(LoginRequest)}.
   *
   * <ul>
   *   <li>Given array of {@link String} with {@code Bearer}.
   *   <li>When {@link MockMvcRequestBuilders#post(String, Object[])} {@code /auth/login} accept
   *       {@code Bearer}.
   * </ul>
   *
   * <p>Method under test: {@link AuthController#login(LoginRequest)}
   */
  @Test
  @DisplayName(
      "Test login(LoginRequest); given array of String with 'Bearer'; when post(String, Object[]) '/auth/login' accept 'Bearer'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"org.springframework.http.ResponseEntity AuthController.login(LoginRequest)"})
  void testLogin_givenArrayOfStringWithBearer_whenPostAuthLoginAcceptBearer() throws Exception {
    // Arrange
    when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
    when(jwtService.getExpirationMs()).thenReturn(1L);
    User user = new User("janedoe", "iloveyou", new ArrayList<>());
    when(authenticationManager.authenticate(Mockito.<Authentication>any()))
        .thenReturn(new TestingAuthenticationToken(user, "Credentials"));

    MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/auth/login");
    postResult.accept("Bearer");

    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setPassword("iloveyou");
    loginRequest.setUsername("janedoe");

    MockHttpServletRequestBuilder requestBuilder =
        postResult
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                JsonMapper.builder().findAndAddModules().build().writeValueAsString(loginRequest));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authController)
        .setControllerAdvice(globalExceptionHandler, securityExceptionHandler)
        .build()
        .perform(requestBuilder)
        .andExpect(status().isInternalServerError());
  }
}
