package com.emp.service;

import com.emp.dto.EmployeeDto;
import com.emp.utility.DetailsException;

import java.util.List;

public interface EmpService {
    List<EmployeeDto> getAllEmployee();

    EmployeeDto getEmployee(int id);

    EmployeeDto addEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(int empId, EmployeeDto employeeDto);

    EmployeeDto deleteEmployee(int id);

    List<EmployeeDto> getEmployeeByDeptId(int deptId);

}
