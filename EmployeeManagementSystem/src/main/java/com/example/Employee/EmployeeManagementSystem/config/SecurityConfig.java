package com.example.Employee.EmployeeManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {




    // Password encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security rules
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Public paths
                        .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()

                        // Role-based access
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/employee/**", "/apply", "/history", "/").hasRole("EMPLOYEE")

                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")                          // Custom login page
                        .defaultSuccessUrl("/dashboard", true)        // Redirect after login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")            // Redirect after logout
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());                    // Disable CSRF for simplicity (optional)

        return http.build();
    }
}
