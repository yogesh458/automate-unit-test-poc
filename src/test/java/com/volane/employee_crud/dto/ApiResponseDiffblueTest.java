package com.volane.employee_crud.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ApiResponseDiffblueTest {
  /**
   * Test {@link ApiResponse#success(int, String, Object)}.
   *
   * <p>Method under test: {@link ApiResponse#success(int, String, Object)}
   */
  @Test
  @DisplayName("Test success(int, String, Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ApiResponse ApiResponse.success(int, String, Object)"})
  void testSuccess() {
    // Arrange and Act
    ApiResponse<Object> actualSuccessResult =
        ApiResponse.success(1, "Not all who wander are lost", "Data");

    // Assert
    assertEquals("Data", actualSuccessResult.getData());
    assertEquals("Not all who wander are lost", actualSuccessResult.getMessage());
    assertEquals(1, actualSuccessResult.getStatus());
  }

  /**
   * Test {@link ApiResponse#error(int, String, Object)}.
   *
   * <p>Method under test: {@link ApiResponse#error(int, String, Object)}
   */
  @Test
  @DisplayName("Test error(int, String, Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ApiResponse ApiResponse.error(int, String, Object)"})
  void testError() {
    // Arrange and Act
    ApiResponse<Object> actualErrorResult =
        ApiResponse.error(1, "Not all who wander are lost", "Data");

    // Assert
    assertEquals("Data", actualErrorResult.getData());
    assertEquals("Not all who wander are lost", actualErrorResult.getMessage());
    assertEquals(1, actualErrorResult.getStatus());
  }
}
