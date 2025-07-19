package com.example.Employee.EmployeeManagementSystem.repository;



import com.example.Employee.EmployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmailAndPassword(String email, String password);

    Employee findByUsername(String username);
}

