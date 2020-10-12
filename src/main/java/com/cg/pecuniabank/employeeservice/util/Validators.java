package com.cg.pecuniabank.employeeservice.util;

import com.cg.pecuniabank.employeeservice.entity.Employee;
import com.cg.pecuniabank.employeeservice.exception.InvalidDataException;
import org.springframework.stereotype.Component;

@Component
public class Validators {
    public boolean validateInputData(Employee employee) throws InvalidDataException {
        boolean valid = true;
        if (employee.getEmployeeName() == null || employee.getEmployeeName().trim().isEmpty()) {
            throw new InvalidDataException("Employee name cannot be empty");
        }
        if (employee.getAddress() == null || employee.getAddress().trim().isEmpty()) {
            throw new InvalidDataException("Address cannot be empty");
        }
        if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
            throw new InvalidDataException("Email cannot be empty");
        }
        if (employee.getMobileNumber() == null || employee.getMobileNumber().trim().isEmpty()) {
            throw new InvalidDataException("Mobile number cannot be empty");
        }
        if (employee.getUsername() == null || employee.getUsername().trim().isEmpty()) {
            throw new InvalidDataException("Username cannot be empty");
        }
        if (employee.getMobileNumber().length() < 10 || employee.getMobileNumber().length() > 10) {
            throw new InvalidDataException("Mobile number should only have 10 digits");
        }
        return true;
    }
}
