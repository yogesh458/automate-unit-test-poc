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
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtAuthenticationEntryPoint.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class JwtAuthenticationEntryPointDiffblueTest {
  @Autowired private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @MockBean private ObjectMapper objectMapper;

  /**
   * Test {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest, HttpServletResponse,
   * AuthenticationException)}.
   *
   * <p>Method under test: {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest,
   * HttpServletResponse, AuthenticationException)}
   */
  @Test
  @DisplayName("Test commence(HttpServletRequest, HttpServletResponse, AuthenticationException)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JwtAuthenticationEntryPoint.commence(HttpServletRequest, HttpServletResponse, AuthenticationException)"
  })
  void testCommence() throws IOException {
    // Arrange
    JsonMapper objectMapper = JsonMapper.builder().findAndAddModules().build();
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint =
        new JwtAuthenticationEntryPoint(objectMapper);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    jwtAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));

    // Assert
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof Set);
    assertEquals("application/json", response.getContentType());
    assertEquals(401, response.getStatus());
  }

  /**
   * Test {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest, HttpServletResponse,
   * AuthenticationException)}.
   *
   * <ul>
   *   <li>Given {@link ObjectMapper} {@link ObjectMapper#writeValue(OutputStream, Object)} throw
   *       {@link IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest,
   * HttpServletResponse, AuthenticationException)}
   */
  @Test
  @DisplayName(
      "Test commence(HttpServletRequest, HttpServletResponse, AuthenticationException); given ObjectMapper writeValue(OutputStream, Object) throw IOException(); then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JwtAuthenticationEntryPoint.commence(HttpServletRequest, HttpServletResponse, AuthenticationException)"
  })
  void testCommence_givenObjectMapperWriteValueThrowIOException_thenThrowIOException()
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
        () ->
            jwtAuthenticationEntryPoint.commence(
                request, response, new AccountExpiredException("Msg")));
    verify(objectMapper).writeValue(isA(OutputStream.class), isA(Object.class));
  }

  /**
   * Test {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest, HttpServletResponse,
   * AuthenticationException)}.
   *
   * <ul>
   *   <li>Then calls {@link JsonFactory#createGenerator(OutputStream, JsonEncoding)}.
   * </ul>
   *
   * <p>Method under test: {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest,
   * HttpServletResponse, AuthenticationException)}
   */
  @Test
  @DisplayName(
      "Test commence(HttpServletRequest, HttpServletResponse, AuthenticationException); then calls createGenerator(OutputStream, JsonEncoding)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JwtAuthenticationEntryPoint.commence(HttpServletRequest, HttpServletResponse, AuthenticationException)"
  })
  void testCommence_thenCallsCreateGenerator() throws IOException {
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
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint =
        new JwtAuthenticationEntryPoint(objectMapper);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act and Assert
    assertThrows(
        IOException.class,
        () ->
            jwtAuthenticationEntryPoint.commence(
                request, response, new AccountExpiredException("Msg")));
    verify(streamFactory).createGenerator(isA(OutputStream.class), eq(JsonEncoding.UTF8));
    verify(streamFactory).getCodec();
    verify(streamFactory).requiresPropertyOrdering();
    verify(builder).findAndAddModules();
  }

  /**
   * Test {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest, HttpServletResponse,
   * AuthenticationException)}.
   *
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) HeaderNames contains {@code
   *       Content-Type}.
   * </ul>
   *
   * <p>Method under test: {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest,
   * HttpServletResponse, AuthenticationException)}
   */
  @Test
  @DisplayName(
      "Test commence(HttpServletRequest, HttpServletResponse, AuthenticationException); then MockHttpServletResponse (default constructor) HeaderNames contains 'Content-Type'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JwtAuthenticationEntryPoint.commence(HttpServletRequest, HttpServletResponse, AuthenticationException)"
  })
  void testCommence_thenMockHttpServletResponseHeaderNamesContainsContentType() throws IOException {
    // Arrange
    doNothing().when(objectMapper).writeValue(Mockito.<OutputStream>any(), Mockito.<Object>any());
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    jwtAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));

    // Assert
    verify(objectMapper).writeValue(isA(OutputStream.class), isA(Object.class));
    Collection<String> headerNames = response.getHeaderNames();
    assertEquals(1, headerNames.size());
    assertTrue(headerNames instanceof Set);
    assertEquals("application/json", response.getContentType());
    assertEquals(401, response.getStatus());
    assertTrue(headerNames.contains("Content-Type"));
  }
}
