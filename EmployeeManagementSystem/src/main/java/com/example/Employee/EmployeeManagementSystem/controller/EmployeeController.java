package com.example.Employee.EmployeeManagementSystem.controller;

import com.example.Employee.EmployeeManagementSystem.model.Employee;
import com.example.Employee.EmployeeManagementSystem.model.LeaveRequest;
import com.example.Employee.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.Employee.EmployeeManagementSystem.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void registerEmployee(Employee emp) {
        // Encode password before saving
        emp.setPassword(passwordEncoder.encode(emp.getPassword()));

        // Save to DB
        employeeRepository.save(emp);
    }


    @Autowired
    private LeaveRepository leaveRepo;

    // Show employee dashboard with leave requests
    @GetMapping("/employee/home")
    public String employeeHome(Model model, Authentication authentication) {
        String username = authentication.getName();
        model.addAttribute("list", leaveRepo.findByEmployeeName(username));
        return "employee_home";
    }

    // Show leave application form
    @GetMapping("/employee/apply")
    public String applyForm(Model model) {
        model.addAttribute("leave", new LeaveRequest());
        return "apply_leave";
    }

    // Handle leave application submission
    @PostMapping("/employee/apply")
    public String applyLeave(@ModelAttribute("leave") LeaveRequest leave, Authentication authentication) {
        leave.setEmployeeName(authentication.getName());
        leave.setStatus("Pending");
        leaveRepo.save(leave);
        return "redirect:/employee/home";
    }
}

