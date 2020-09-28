package com.cg.pecuniabank.employeeservice.entity;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_ID_SEQ")
    @SequenceGenerator(name = "EMPLOYEE_ID_SEQ", sequenceName = "EMPLOYEE_ID_SEQ", allocationSize = 1,initialValue = 1721)
    private long employeeId;
    private String employeeName;
    private String mobileNumber;
    private String email;
    private String address;
    @Column(unique = true)
    private String username;

    public Employee() {
    }

    public Employee(String employeeName, String mobileNumber, String email, String address, String username) {
        this.employeeName = employeeName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.address = address;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
