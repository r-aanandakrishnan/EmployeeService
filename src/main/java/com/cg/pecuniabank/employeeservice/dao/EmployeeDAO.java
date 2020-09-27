package com.cg.pecuniabank.employeeservice.dao;

import com.cg.pecuniabank.employeeservice.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeDAO extends CrudRepository<Employee,Long> {
}
