package com.volane.employee_crud.dto.auth;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LoginRequestDiffblueTest {
  /**
   * Test new {@link LoginRequest} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link LoginRequest}
   */
  @Test
  @DisplayName("Test new LoginRequest (default constructor)")
  @Tag("MaintainedByDiffblue")
  void testNewLoginRequest() {
    // Arrange and Act
    LoginRequest actualLoginRequest = new LoginRequest();

    // Assert
    assertNull(actualLoginRequest.getPassword());
    assertNull(actualLoginRequest.getUsername());
  }
}
