package com.volane.employee_crud.security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final String adminUsername;
    private final UserDetails adminUser;

    public AdminUserDetailsService(
            PasswordEncoder passwordEncoder,
            @Value("${security.admin.username:admin}") String adminUsername,
            @Value("${security.admin.password:admin123}") String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminUser = User.builder()
                .username(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .roles("ADMIN")
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!adminUsername.equals(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return adminUser;
    }
}
