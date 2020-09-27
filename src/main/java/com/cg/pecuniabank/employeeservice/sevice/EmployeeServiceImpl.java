package com.cg.pecuniabank.employeeservice.sevice;

import com.cg.pecuniabank.employeeservice.dao.EmployeeDAO;
import com.cg.pecuniabank.employeeservice.entity.Employee;
import com.cg.pecuniabank.employeeservice.util.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    Validators validators;

    @Override
    public Employee addEmployee(Employee employee) throws Exception {
        validators.validateInputData(employee);
        return employeeDAO.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employeeOptional = employeeDAO.findById(id);
        return employeeOptional.get();
    }

    @Override
    public Employee updateEmployee(long employeeId, Employee employee) throws Exception {
        validators.validateInputData(employee);
        Employee employeeOptional = getEmployeeById(employeeId);
        employee.setEmployeeId(employeeId);
        return employeeDAO.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeDAO.findAll();
    }

    @Override
    public Employee deleteEmployee(long id) {
        Optional<Employee> employee = employeeDAO.findById(id);
        if (employee.get() != null) {
            employeeDAO.delete(employee.get());
        }
        return employee.get();
    }
}
