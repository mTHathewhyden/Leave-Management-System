package com.example.Employee.EmployeeManagementSystem.model;

import jakarta.persistence.*;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;

    private String fromDate;

    private String toDate;

    private String reason;

    private String status = "Pending"; // Default status

    // Constructors
    public LeaveRequest() {}

    public LeaveRequest(String employeeName, String fromDate, String toDate, String reason) {
        this.employeeName = employeeName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.status = "Pending";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
