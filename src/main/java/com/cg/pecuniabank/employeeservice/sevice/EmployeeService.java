package com.cg.pecuniabank.employeeservice.sevice;

import com.cg.pecuniabank.employeeservice.entity.Employee;
import com.cg.pecuniabank.employeeservice.exception.EmployeeNotFoundException;
import com.cg.pecuniabank.employeeservice.exception.InvalidDataException;
import com.cg.pecuniabank.employeeservice.exception.ReportGenerationException;
import com.cg.pecuniabank.employeeservice.exception.UsernameAlreadyTakenException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(Employee employee) throws InvalidDataException, UsernameAlreadyTakenException;

    public Employee getEmployeeById(long id) throws EmployeeNotFoundException;

    public Employee updateEmployee(long employeeId, Employee employee) throws InvalidDataException,UsernameAlreadyTakenException;

    public List<Employee> getAllEmployees();

    public Employee deleteEmployee(long id);

    public ByteArrayInputStream generateAssetReport() throws ReportGenerationException;
}
