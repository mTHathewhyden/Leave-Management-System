package com.example.Employee.EmployeeManagementSystem;

import com.example.Employee.EmployeeManagementSystem.model.Employee;
import com.example.Employee.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

	// Add this Bean inside the main class
	@Bean
	public CommandLineRunner init(EmployeeRepository repo, BCryptPasswordEncoder encoder) {
		return args -> {
			if (repo.findByUsername("employee") == null) {
				Employee emp = new Employee();
				emp.setUsername("employee");
				emp.setPassword(encoder.encode("emp123"));
				emp.setRole("EMPLOYEE");
				emp.setEmail("emp@company.com");
				repo.save(emp);
			}

			if (repo.findByUsername("admin") == null) {
				Employee admin = new Employee();
				admin.setUsername("admin");
				admin.setPassword(encoder.encode("Admin123"));
				admin.setRole("ADMIN");
				admin.setEmail("admin@company.com");
				repo.save(admin);
			}
		};
	}
}
