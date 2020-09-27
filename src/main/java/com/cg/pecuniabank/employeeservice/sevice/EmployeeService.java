package com.cg.pecuniabank.employeeservice.sevice;

import com.cg.pecuniabank.employeeservice.entity.Employee;
import com.cg.pecuniabank.employeeservice.exception.InvalidDataException;
import com.cg.pecuniabank.employeeservice.exception.ReportGenerationException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(Employee employee) throws InvalidDataException;

    public Employee getEmployeeById(long id);

    public Employee updateEmployee(long employeeId, Employee employee) throws InvalidDataException;

    public List<Employee> getAllEmployees();

    public Employee deleteEmployee(long id);

    public ByteArrayInputStream generateAssetReport() throws ReportGenerationException;
}
