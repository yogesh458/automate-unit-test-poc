package com.volane.employee_crud.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionDiffblueTest {
  /**
   * Test {@link ResourceNotFoundException#ResourceNotFoundException(String)}.
   *
   * <p>Method under test: {@link ResourceNotFoundException#ResourceNotFoundException(String)}
   */
  @Test
  @DisplayName("Test new ResourceNotFoundException(String)")
  @Tag("MaintainedByDiffblue")
  void testNewResourceNotFoundException() {
    // Arrange and Act
    ResourceNotFoundException actualResourceNotFoundException =
        new ResourceNotFoundException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualResourceNotFoundException.getMessage());
    assertNull(actualResourceNotFoundException.getCause());
    assertEquals(0, actualResourceNotFoundException.getSuppressed().length);
  }
}
