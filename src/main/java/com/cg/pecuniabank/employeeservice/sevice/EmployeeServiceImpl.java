package com.cg.pecuniabank.employeeservice.sevice;

import com.cg.pecuniabank.employeeservice.dao.EmployeeDAO;
import com.cg.pecuniabank.employeeservice.entity.Employee;
import com.cg.pecuniabank.employeeservice.exception.InvalidDataException;
import com.cg.pecuniabank.employeeservice.exception.ReportGenerationException;
import com.cg.pecuniabank.employeeservice.util.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    Validators validators;

    @Override
    public Employee addEmployee(Employee employee) throws InvalidDataException {
        validators.validateInputData(employee);
        return employeeDAO.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employeeOptional = employeeDAO.findById(id);
        return employeeOptional.get();
    }

    @Override
    public Employee updateEmployee(long employeeId, Employee employee) throws InvalidDataException {
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

    @Override
    public ByteArrayInputStream generateAssetReport() throws ReportGenerationException {
        Iterable<Employee> allEmployees = employeeDAO.findAll();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employee Details");
        String headers[] = new String[]{"Employee ID","Employee Name","Mobile Number","Email","Address","Username"};
        Row row = sheet.createRow(0);
        int cellNum=0;
        for(String header: headers){
            Cell cell = row.createCell(cellNum++);
            cell.setCellValue(header);
        }
        int rowNum = 1;
        List<String[]> employeeData = new ArrayList<>();
        allEmployees.forEach(employee-> employeeData.add(new String[]{
                employee.getEmployeeId()+"",
                employee.getEmployeeName(),
                employee.getMobileNumber(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getUsername()
        }));
        for(String[] employee:employeeData){
            cellNum=0;
            row = sheet.createRow(rowNum++);
            for(String data:employee){
                Cell cell = row.createCell(cellNum++);
                cell.setCellValue(data);
            }
        }
        ByteArrayOutputStream bos;
        byte[] byteArrayData;
        try {
            bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byteArrayData = bos.toByteArray();
            bos.close();
        }
        catch(IOException exception){
            throw new ReportGenerationException("An error occurred when generating Employee Report",exception);
        }
        return new ByteArrayInputStream(byteArrayData);
    }
}
