package com.example.Employee.EmployeeManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This should resolve to templates/login.html
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        // Redirect to a role-specific dashboard
        return "dashboard"; // Create dashboard.html under templates
    }
}
