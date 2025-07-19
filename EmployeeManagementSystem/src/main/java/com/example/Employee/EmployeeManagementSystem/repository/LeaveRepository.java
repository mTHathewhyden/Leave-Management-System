package com.example.Employee.EmployeeManagementSystem.repository;


import com.example.Employee.EmployeeManagementSystem.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeName(String employeeName);
}
