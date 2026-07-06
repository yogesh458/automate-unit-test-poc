package com.volane.employee_crud.security.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collection;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AdminUserDetailsServiceDiffblueTest {
  /**
   * Test {@link AdminUserDetailsService#loadUserByUsername(String)}.
   *
   * <ul>
   *   <li>Then return Authorities size is one.
   * </ul>
   *
   * <p>Method under test: {@link AdminUserDetailsService#loadUserByUsername(String)}
   */
  @Test
  @DisplayName("Test loadUserByUsername(String); then return Authorities size is one")
  @Tag("MaintainedByDiffblue")
  void testLoadUserByUsername_thenReturnAuthoritiesSizeIsOne() throws UsernameNotFoundException {
    // Arrange
    AdminUserDetailsService adminUserDetailsService =
        new AdminUserDetailsService(new BCryptPasswordEncoder(), "janedoe", "iloveyou");

    // Act
    UserDetails actualLoadUserByUsernameResult =
        adminUserDetailsService.loadUserByUsername("janedoe");

    // Assert
    Collection<? extends GrantedAuthority> authorities =
        actualLoadUserByUsernameResult.getAuthorities();
    assertEquals(1, authorities.size());
    assertTrue(authorities instanceof Set);
    assertTrue(actualLoadUserByUsernameResult instanceof User);
    assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
    assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
    assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
    assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
    assertTrue(actualLoadUserByUsernameResult.isEnabled());
  }

  /**
   * Test {@link AdminUserDetailsService#loadUserByUsername(String)}.
   *
   * <ul>
   *   <li>Then throw {@link UsernameNotFoundException}.
   * </ul>
   *
   * <p>Method under test: {@link AdminUserDetailsService#loadUserByUsername(String)}
   */
  @Test
  @DisplayName("Test loadUserByUsername(String); then throw UsernameNotFoundException")
  @Tag("MaintainedByDiffblue")
  void testLoadUserByUsername_thenThrowUsernameNotFoundException()
      throws UsernameNotFoundException {
    // Arrange
    AdminUserDetailsService adminUserDetailsService =
        new AdminUserDetailsService(new BCryptPasswordEncoder(), "Admin Username", "iloveyou");

    // Act and Assert
    assertThrows(
        UsernameNotFoundException.class,
        () -> adminUserDetailsService.loadUserByUsername("janedoe"));
  }
}
