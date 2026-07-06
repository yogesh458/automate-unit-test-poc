package com.volane.employee_crud.security.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
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
  @Tag("MaintainedByDiffblue")
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
   *   <li>Given {@code Object}.
   *   <li>Then calls {@link Builder#findAndAddModules()}.
   * </ul>
   *
   * <p>Method under test: {@link JwtAuthenticationEntryPoint#commence(HttpServletRequest,
   * HttpServletResponse, AuthenticationException)}
   */
  @Test
  @DisplayName(
      "Test commence(HttpServletRequest, HttpServletResponse, AuthenticationException); given 'java.lang.Object'; then calls findAndAddModules()")
  @Tag("MaintainedByDiffblue")
  void testCommence_givenJavaLangObject_thenCallsFindAndAddModules() throws IOException {
    // Arrange
    JsonMapper m = JsonMapper.builder().findAndAddModules().build();

    Builder builder = new Builder(m);
    Class<Object> target = Object.class;
    Class<Object> mixinSource = Object.class;

    builder.addMixIn(target, mixinSource);

    Builder builder2 = mock(Builder.class);
    when(builder2.findAndAddModules()).thenReturn(builder);
    JsonMapper objectMapper = builder2.findAndAddModules().build();
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint =
        new JwtAuthenticationEntryPoint(objectMapper);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    jwtAuthenticationEntryPoint.commence(request, response, new AccountExpiredException("Msg"));

    // Assert
    verify(builder2).findAndAddModules();
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
  @Tag("MaintainedByDiffblue")
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
  @Tag("MaintainedByDiffblue")
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
