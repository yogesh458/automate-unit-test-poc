package com.volane.employee_crud.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.volane.employee_crud.dto.ApiResponse;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ContextConfiguration(classes = {GlobalExceptionHandler.class})
@ExtendWith(SpringExtension.class)
class GlobalExceptionHandlerDiffblueTest {
  @Autowired private GlobalExceptionHandler globalExceptionHandler;

  /**
   * Test {@link GlobalExceptionHandler#handleResourceNotFoundException(ResourceNotFoundException)}.
   *
   * <ul>
   *   <li>Then StatusCode return {@link HttpStatus}.
   * </ul>
   *
   * <p>Method under test: {@link
   * GlobalExceptionHandler#handleResourceNotFoundException(ResourceNotFoundException)}
   */
  @Test
  @DisplayName(
      "Test handleResourceNotFoundException(ResourceNotFoundException); then StatusCode return HttpStatus")
  @Tag("MaintainedByDiffblue")
  void testHandleResourceNotFoundException_thenStatusCodeReturnHttpStatus() {
    // Arrange and Act
    ResponseEntity<ApiResponse<Void>> actualHandleResourceNotFoundExceptionResult =
        globalExceptionHandler.handleResourceNotFoundException(
            new ResourceNotFoundException("An error occurred"));

    // Assert
    HttpStatusCode statusCode = actualHandleResourceNotFoundExceptionResult.getStatusCode();
    assertTrue(statusCode instanceof HttpStatus);
    ApiResponse<Void> body = actualHandleResourceNotFoundExceptionResult.getBody();
    assertEquals("An error occurred", body.getMessage());
    assertNull(body.getData());
    assertEquals(404, body.getStatus());
    assertEquals(404, actualHandleResourceNotFoundExceptionResult.getStatusCodeValue());
    assertEquals(HttpStatus.NOT_FOUND, statusCode);
    assertTrue(actualHandleResourceNotFoundExceptionResult.hasBody());
    assertTrue(actualHandleResourceNotFoundExceptionResult.getHeaders().isEmpty());
  }

  /**
   * Test {@link
   * GlobalExceptionHandler#handleDuplicateResourceException(DuplicateResourceException)}.
   *
   * <ul>
   *   <li>Then StatusCode return {@link HttpStatus}.
   * </ul>
   *
   * <p>Method under test: {@link
   * GlobalExceptionHandler#handleDuplicateResourceException(DuplicateResourceException)}
   */
  @Test
  @DisplayName(
      "Test handleDuplicateResourceException(DuplicateResourceException); then StatusCode return HttpStatus")
  @Tag("MaintainedByDiffblue")
  void testHandleDuplicateResourceException_thenStatusCodeReturnHttpStatus() {
    // Arrange and Act
    ResponseEntity<ApiResponse<Void>> actualHandleDuplicateResourceExceptionResult =
        globalExceptionHandler.handleDuplicateResourceException(
            new DuplicateResourceException("An error occurred"));

    // Assert
    HttpStatusCode statusCode = actualHandleDuplicateResourceExceptionResult.getStatusCode();
    assertTrue(statusCode instanceof HttpStatus);
    ApiResponse<Void> body = actualHandleDuplicateResourceExceptionResult.getBody();
    assertEquals("An error occurred", body.getMessage());
    assertNull(body.getData());
    assertEquals(409, body.getStatus());
    assertEquals(409, actualHandleDuplicateResourceExceptionResult.getStatusCodeValue());
    assertEquals(HttpStatus.CONFLICT, statusCode);
    assertTrue(actualHandleDuplicateResourceExceptionResult.hasBody());
    assertTrue(actualHandleDuplicateResourceExceptionResult.getHeaders().isEmpty());
  }

  /**
   * Test {@link GlobalExceptionHandler#handleValidationException(MethodArgumentNotValidException)}.
   *
   * <ul>
   *   <li>Then StatusCode return {@link HttpStatus}.
   * </ul>
   *
   * <p>Method under test: {@link
   * GlobalExceptionHandler#handleValidationException(MethodArgumentNotValidException)}
   */
  @Test
  @DisplayName(
      "Test handleValidationException(MethodArgumentNotValidException); then StatusCode return HttpStatus")
  @Tag("MaintainedByDiffblue")
  void testHandleValidationException_thenStatusCodeReturnHttpStatus() {
    // Arrange
    MethodArgumentNotValidException exception =
        new MethodArgumentNotValidException(null, new BindException("Target", "Object Name"));

    // Act
    ResponseEntity<ApiResponse<Map<String, String>>> actualHandleValidationExceptionResult =
        globalExceptionHandler.handleValidationException(exception);

    // Assert
    HttpStatusCode statusCode = actualHandleValidationExceptionResult.getStatusCode();
    assertTrue(statusCode instanceof HttpStatus);
    ApiResponse<Map<String, String>> body = actualHandleValidationExceptionResult.getBody();
    assertEquals("Validation failed", body.getMessage());
    assertEquals(400, body.getStatus());
    assertEquals(400, actualHandleValidationExceptionResult.getStatusCodeValue());
    assertEquals(HttpStatus.BAD_REQUEST, statusCode);
    assertTrue(body.getData().isEmpty());
    assertTrue(actualHandleValidationExceptionResult.hasBody());
    assertTrue(actualHandleValidationExceptionResult.getHeaders().isEmpty());
  }

  /**
   * Test {@link GlobalExceptionHandler#handleValidationException(MethodArgumentNotValidException)}.
   *
   * <ul>
   *   <li>Then throw {@link ResourceNotFoundException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * GlobalExceptionHandler#handleValidationException(MethodArgumentNotValidException)}
   */
  @Test
  @DisplayName(
      "Test handleValidationException(MethodArgumentNotValidException); then throw ResourceNotFoundException")
  @Tag("MaintainedByDiffblue")
  void testHandleValidationException_thenThrowResourceNotFoundException() {
    // Arrange
    BeanPropertyBindingResult bindingResult = mock(BeanPropertyBindingResult.class);
    when(bindingResult.getFieldErrors())
        .thenThrow(new ResourceNotFoundException("An error occurred"));
    MethodArgumentNotValidException exception =
        new MethodArgumentNotValidException(null, bindingResult);

    // Act and Assert
    assertThrows(
        ResourceNotFoundException.class,
        () -> globalExceptionHandler.handleValidationException(exception));
    verify(bindingResult).getFieldErrors();
  }

  /**
   * Test {@link GlobalExceptionHandler#handleGenericException(Exception)}.
   *
   * <p>Method under test: {@link GlobalExceptionHandler#handleGenericException(Exception)}
   */
  @Test
  @DisplayName("Test handleGenericException(Exception)")
  @Tag("MaintainedByDiffblue")
  void testHandleGenericException() {
    // Arrange and Act
    ResponseEntity<ApiResponse<Void>> actualHandleGenericExceptionResult =
        globalExceptionHandler.handleGenericException(new Exception());

    // Assert
    HttpStatusCode statusCode = actualHandleGenericExceptionResult.getStatusCode();
    assertTrue(statusCode instanceof HttpStatus);
    ApiResponse<Void> body = actualHandleGenericExceptionResult.getBody();
    assertEquals("An unexpected error occurred", body.getMessage());
    assertNull(body.getData());
    assertEquals(500, body.getStatus());
    assertEquals(500, actualHandleGenericExceptionResult.getStatusCodeValue());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
    assertTrue(actualHandleGenericExceptionResult.hasBody());
    assertTrue(actualHandleGenericExceptionResult.getHeaders().isEmpty());
  }
}
