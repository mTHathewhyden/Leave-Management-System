package com.example.Employee.EmployeeManagementSystem.service;

import com.example.Employee.EmployeeManagementSystem.model.Employee;
import com.example.Employee.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp = employeeRepository.findByUsername(username);
        if (emp == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Get the role from the employee entity
        String role = emp.getRole(); // Make sure your Employee entity has a role field

        return User.builder()
                .username(emp.getUsername())
                .password(emp.getPassword()) // This should be BCrypt encoded in database
                .roles(role) // Use the actual role from database
                .build();
    }
}