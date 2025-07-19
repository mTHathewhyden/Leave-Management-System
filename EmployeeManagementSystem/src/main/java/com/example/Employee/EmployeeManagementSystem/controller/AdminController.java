package com.example.Employee.EmployeeManagementSystem.controller;

import com.example.Employee.EmployeeManagementSystem.model.LeaveRequest;
import com.example.Employee.EmployeeManagementSystem.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private LeaveRepository leaveRepo;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("requests", leaveRepo.findAll());
        return "admin_dashboard";
    }

    @GetMapping("/admin/approve/{id}")
    public String approve(@PathVariable Long id) {
        LeaveRequest leave = leaveRepo.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus("Approved");
            leaveRepo.save(leave);
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/reject/{id}")
    public String reject(@PathVariable Long id) {
        LeaveRequest leave = leaveRepo.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus("Rejected");
            leaveRepo.save(leave);
        }
        return "redirect:/admin/dashboard";
    }
}
