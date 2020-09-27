package com.cg.pecuniabank.employeeservice.sevice;

import com.cg.pecuniabank.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(Employee employee) throws Exception;

    public Employee getEmployeeById(long id);

    public Employee updateEmployee(long employeeId, Employee employee) throws Exception;

    public List<Employee> getAllEmployees();

    public Employee deleteEmployee(long id);
}
