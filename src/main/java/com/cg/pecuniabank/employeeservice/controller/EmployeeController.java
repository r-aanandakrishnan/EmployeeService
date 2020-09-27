package com.cg.pecuniabank.employeeservice.controller;

import com.cg.pecuniabank.employeeservice.entity.Employee;
import com.cg.pecuniabank.employeeservice.sevice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping(
            value = "/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Employee getEmployeeWithId(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }
    @PostMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Employee addNewEmployee(@RequestBody Employee employee) throws Exception{
        return employeeService.addEmployee(employee);
    }
    @DeleteMapping(
            value = "/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Employee removeEmployee(@PathVariable long id){
        return employeeService.deleteEmployee(id);
    }
    @PutMapping(
            value="/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Employee updateEmployee(@PathVariable long id,@RequestBody Employee modifiedEmployee) throws Exception{
        return employeeService.updateEmployee(id,modifiedEmployee);
    }
}
