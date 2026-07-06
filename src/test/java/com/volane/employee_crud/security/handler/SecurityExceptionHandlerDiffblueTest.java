package com.volane.employee_crud.security.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.volane.employee_crud.dto.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityExceptionHandler.class})
@ExtendWith(SpringExtension.class)
class SecurityExceptionHandlerDiffblueTest {
  @Autowired private SecurityExceptionHandler securityExceptionHandler;

  /**
   * Test {@link SecurityExceptionHandler#handleAuthenticationException(AuthenticationException)}.
   *
   * <p>Method under test: {@link
   * SecurityExceptionHandler#handleAuthenticationException(AuthenticationException)}
   */
  @Test
  @DisplayName("Test handleAuthenticationException(AuthenticationException)")
  @Tag("MaintainedByDiffblue")
  void testHandleAuthenticationException() {
    // Arrange and Act
    ResponseEntity<ApiResponse<Void>> actualHandleAuthenticationExceptionResult =
        securityExceptionHandler.handleAuthenticationException(new AccountExpiredException("Msg"));

    // Assert
    HttpStatusCode statusCode = actualHandleAuthenticationExceptionResult.getStatusCode();
    assertTrue(statusCode instanceof HttpStatus);
    ApiResponse<Void> body = actualHandleAuthenticationExceptionResult.getBody();
    assertEquals("Invalid username or password", body.getMessage());
    assertNull(body.getData());
    assertEquals(401, body.getStatus());
    assertEquals(401, actualHandleAuthenticationExceptionResult.getStatusCodeValue());
    assertEquals(HttpStatus.UNAUTHORIZED, statusCode);
    assertTrue(actualHandleAuthenticationExceptionResult.hasBody());
    assertTrue(actualHandleAuthenticationExceptionResult.getHeaders().isEmpty());
  }

  /**
   * Test {@link SecurityExceptionHandler#handleAccessDeniedException(AccessDeniedException)}.
   *
   * <p>Method under test: {@link
   * SecurityExceptionHandler#handleAccessDeniedException(AccessDeniedException)}
   */
  @Test
  @DisplayName("Test handleAccessDeniedException(AccessDeniedException)")
  @Tag("MaintainedByDiffblue")
  void testHandleAccessDeniedException() {
    // Arrange and Act
    ResponseEntity<ApiResponse<Void>> actualHandleAccessDeniedExceptionResult =
        securityExceptionHandler.handleAccessDeniedException(new AccessDeniedException("Msg"));

    // Assert
    HttpStatusCode statusCode = actualHandleAccessDeniedExceptionResult.getStatusCode();
    assertTrue(statusCode instanceof HttpStatus);
    ApiResponse<Void> body = actualHandleAccessDeniedExceptionResult.getBody();
    assertEquals(
        "Forbidden: you do not have permission to access this resource", body.getMessage());
    assertNull(body.getData());
    assertEquals(403, body.getStatus());
    assertEquals(403, actualHandleAccessDeniedExceptionResult.getStatusCodeValue());
    assertEquals(HttpStatus.FORBIDDEN, statusCode);
    assertTrue(actualHandleAccessDeniedExceptionResult.hasBody());
    assertTrue(actualHandleAccessDeniedExceptionResult.getHeaders().isEmpty());
  }
}
