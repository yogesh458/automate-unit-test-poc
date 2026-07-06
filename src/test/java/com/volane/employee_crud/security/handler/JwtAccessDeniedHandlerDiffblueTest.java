package com.volane.employee_crud.security.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.json.JsonMapper.Builder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtAccessDeniedHandler.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class JwtAccessDeniedHandlerDiffblueTest {
  @Autowired private JwtAccessDeniedHandler jwtAccessDeniedHandler;

  @MockBean private ObjectMapper objectMapper;

  /**
   * Test {@link JwtAccessDeniedHandler#handle(HttpServletRequest, HttpServletResponse,
   * AccessDeniedException)}.
   *
   * <p>Method under test: {@link JwtAccessDeniedHandler#handle(HttpServletRequest,
   * HttpServletResponse, AccessDeniedException)}
   */
  @Test
  @DisplayName("Test handle(HttpServletRequest, HttpServletResponse, AccessDeniedException)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JwtAccessDeniedHandler.handle(HttpServletRequest, HttpServletResponse, AccessDeniedException)"
  })
  void testHandle() throws IOException {
    // Arrange
    JsonMapper objectMapper = JsonMapper.builder().findAndAddModules().build();
    JwtAccessDeniedHandler jwtAccessDeniedHandler = new JwtAccessDeniedHandler(objectMapper);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    jwtAccessDeniedHandler.handle(request, response, new AccessDeniedException("Msg"));

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof Set);
    assertEquals("application/json", response.getContentType());
    assertEquals(403, response.getStatus());
  }

  /**
   * Test {@link JwtAccessDeniedHandler#handle(HttpServletRequest, HttpServletResponse,
   * AccessDeniedException)}.
   *
   * <ul>
   *   <li>Given {@link ObjectMapper} {@link ObjectMapper#writeValue(OutputStream, Object)} throw
   *       {@link IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link JwtAccessDeniedHandler#handle(HttpServletRequest,
   * HttpServletResponse, AccessDeniedException)}
   */
  @Test
  @DisplayName(
      "Test handle(HttpServletRequest, HttpServletResponse, AccessDeniedException); given ObjectMapper writeValue(OutputStream, Object) throw IOException(); then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JwtAccessDeniedHandler.handle(HttpServletRequest, HttpServletResponse, AccessDeniedException)"
  })
  void testHandle_givenObjectMapperWriteValueThrowIOException_thenThrowIOException()
      throws IOException {
    // Arrange
    doThrow(new IOException())
        .when(objectMapper)
        .writeValue(Mockito.<OutputStream>any(), Mockito.<Object>any());
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act and Assert
    assertThrows(
        IOException.class,
        () -> jwtAccessDeniedHandler.handle(request, response, new AccessDeniedException("Msg")));
    verify(objectMapper).writeValue(isA(OutputStream.class), isA(Object.class));
  }

  /**
   * Test {@link JwtAccessDeniedHandler#handle(HttpServletRequest, HttpServletResponse,
   * AccessDeniedException)}.
   *
   * <ul>
   *   <li>Then calls {@link JsonFactory#createGenerator(OutputStream, JsonEncoding)}.
   * </ul>
   *
   * <p>Method under test: {@link JwtAccessDeniedHandler#handle(HttpServletRequest,
   * HttpServletResponse, AccessDeniedException)}
   */
  @Test
  @DisplayName(
      "Test handle(HttpServletRequest, HttpServletResponse, AccessDeniedException); then calls createGenerator(OutputStream, JsonEncoding)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JwtAccessDeniedHandler.handle(HttpServletRequest, HttpServletResponse, AccessDeniedException)"
  })
  void testHandle_thenCallsCreateGenerator() throws IOException {
    // Arrange
    JsonFactory streamFactory = mock(JsonFactory.class);
    when(streamFactory.requiresPropertyOrdering()).thenReturn(true);
    when(streamFactory.createGenerator(Mockito.<OutputStream>any(), Mockito.<JsonEncoding>any()))
        .thenThrow(new IOException());
    when(streamFactory.getCodec()).thenReturn(JsonMapper.builder().findAndAddModules().build());

    Builder builderResult = JsonMapper.builder(streamFactory);
    builderResult.annotationIntrospector(mock(AnnotationIntrospectorPair.class));

    Builder builder = mock(Builder.class);
    when(builder.findAndAddModules()).thenReturn(builderResult);
    JsonMapper objectMapper = builder.findAndAddModules().build();
    JwtAccessDeniedHandler jwtAccessDeniedHandler = new JwtAccessDeniedHandler(objectMapper);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act and Assert
    assertThrows(
        IOException.class,
        () -> jwtAccessDeniedHandler.handle(request, response, new AccessDeniedException("Msg")));
    verify(streamFactory).createGenerator(isA(OutputStream.class), eq(JsonEncoding.UTF8));
    verify(streamFactory).getCodec();
    verify(streamFactory).requiresPropertyOrdering();
    verify(builder).findAndAddModules();
  }

  /**
   * Test {@link JwtAccessDeniedHandler#handle(HttpServletRequest, HttpServletResponse,
   * AccessDeniedException)}.
   *
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) HeaderNames contains {@code
   *       Content-Type}.
   * </ul>
   *
   * <p>Method under test: {@link JwtAccessDeniedHandler#handle(HttpServletRequest,
   * HttpServletResponse, AccessDeniedException)}
   */
  @Test
  @DisplayName(
      "Test handle(HttpServletRequest, HttpServletResponse, AccessDeniedException); then MockHttpServletResponse (default constructor) HeaderNames contains 'Content-Type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JwtAccessDeniedHandler.handle(HttpServletRequest, HttpServletResponse, AccessDeniedException)"
  })
  void testHandle_thenMockHttpServletResponseHeaderNamesContainsContentType() throws IOException {
    // Arrange
    doNothing().when(objectMapper).writeValue(Mockito.<OutputStream>any(), Mockito.<Object>any());
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    jwtAccessDeniedHandler.handle(request, response, new AccessDeniedException("Msg"));

    // Assert
    verify(objectMapper).writeValue(isA(OutputStream.class), isA(Object.class));
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof Set);
    assertEquals("application/json", response.getContentType());
    assertEquals(403, response.getStatus());
    assertTrue(headerNames.contains("Content-Type"));
  }
}
