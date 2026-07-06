package com.volane.employee_crud.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DuplicateResourceExceptionDiffblueTest {
  /**
   * Test {@link DuplicateResourceException#DuplicateResourceException(String)}.
   *
   * <p>Method under test: {@link DuplicateResourceException#DuplicateResourceException(String)}
   */
  @Test
  @DisplayName("Test new DuplicateResourceException(String)")
  @Tag("MaintainedByDiffblue")
  void testNewDuplicateResourceException() {
    // Arrange and Act
    DuplicateResourceException actualDuplicateResourceException =
        new DuplicateResourceException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualDuplicateResourceException.getMessage());
    assertNull(actualDuplicateResourceException.getCause());
    assertEquals(0, actualDuplicateResourceException.getSuppressed().length);
  }
}
