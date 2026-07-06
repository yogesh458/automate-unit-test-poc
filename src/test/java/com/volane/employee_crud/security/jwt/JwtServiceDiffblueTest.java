package com.volane.employee_crud.security.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class JwtServiceDiffblueTest {
  /**
   * Test {@link JwtService#getExpirationMs()}.
   *
   * <p>Method under test: {@link JwtService#getExpirationMs()}
   */
  @Test
  @DisplayName("Test getExpirationMs()")
  @Tag("MaintainedByDiffblue")
  void testGetExpirationMs() {
    // Arrange, Act and Assert
    assertEquals(1L, new JwtService("Jwt Secret", 1L).getExpirationMs());
  }
}
