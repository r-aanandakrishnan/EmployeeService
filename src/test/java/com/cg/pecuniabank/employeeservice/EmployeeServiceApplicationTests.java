package com.cg.pecuniabank.employeeservice;
import static org.junit.jupiter.api.Assertions.*;
import com.cg.pecuniabank.employeeservice.entity.Employee;
import com.cg.pecuniabank.employeeservice.exception.EmployeeNotFoundException;
import com.cg.pecuniabank.employeeservice.exception.InvalidDataException;
import com.cg.pecuniabank.employeeservice.exception.UsernameAlreadyTakenException;
import com.cg.pecuniabank.employeeservice.sevice.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

@SpringBootTest
class EmployeeServiceApplicationTests {

    @Autowired
    EmployeeService employeeService;
    @Test
    void contextLoads() {
    }
    @Test
    void addEmployeeTest() throws InvalidDataException, UsernameAlreadyTakenException {
        Employee employee= employeeService.addEmployee(new Employee("Aanand","2222228987","aanand@live.com","Chennai","aanand12"));
        assertEquals(1721,employee.getEmployeeId());
    }
    @Test
    void updateEmployeeTest() throws InvalidDataException, UsernameAlreadyTakenException,EmployeeNotFoundException {
        long empId=1721;
        Employee employeeOld;
        employeeService.addEmployee(new Employee("Aanand","2222228987","aanand@live.com","Chennai","aanand12"));
        try {
            employeeOld= employeeService.getEmployeeById(empId);
        }
        catch(Exception e){
            throw new EmployeeNotFoundException("Not Found");
        }
        Employee employee= employeeService.updateEmployee(1721, new Employee("Aanand","2222228987","aanand@live.com","Chennai","aanand12"));
        assertNotEquals(employeeOld,employee);
    }

}
