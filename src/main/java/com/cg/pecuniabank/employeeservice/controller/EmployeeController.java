package com.cg.pecuniabank.employeeservice.controller;
import com.cg.pecuniabank.employeeservice.entity.Employee;
import com.cg.pecuniabank.employeeservice.exception.EmployeeNotFoundException;
import com.cg.pecuniabank.employeeservice.exception.ReportGenerationException;
import com.cg.pecuniabank.employeeservice.sevice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(value="/employees")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    Logger logger=LoggerFactory.getLogger(EmployeeController.class);
    @GetMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public List<Employee> getAllEmployees(){
        logger.info("Displaying all the employees");
        return employeeService.getAllEmployees();
    }
    @GetMapping(
            value = "/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Employee getEmployeeWithId(@PathVariable Long id) throws EmployeeNotFoundException {
        logger.info("Fetching employee with EmpId"+id);
        return employeeService.getEmployeeById(id);
    }
    @PostMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Employee addNewEmployee(@RequestBody Employee employee) throws Exception{
        logger.info("Adding new Employee to the database");
        return employeeService.addEmployee(employee);
    }
    @DeleteMapping(
            value = "/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Employee removeEmployee(@PathVariable long id){
        logger.info("deleting employee with EmpId"+id);
        return employeeService.deleteEmployee(id);
    }
    @PutMapping(
            value="/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Employee updateEmployee(@PathVariable long id,@RequestBody Employee modifiedEmployee) throws Exception{
        logger.info("updating employee with EmpNo"+id);
        return employeeService.updateEmployee(id,modifiedEmployee);
    }
    @GetMapping(
            value = "/report"
    )
    public ResponseEntity<InputStreamResource> getAssetReport() throws ReportGenerationException {
        logger.info("Generating report");
        ByteArrayInputStream in = employeeService.generateAssetReport();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment; filename=employee_report.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
