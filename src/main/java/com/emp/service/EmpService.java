package com.emp.service;

import com.emp.dto.EmployeeDto;
import com.emp.utility.DetailsException;

import java.util.List;

public interface EmpService {
    List<EmployeeDto> getAllEmployee() throws DetailsException;

    EmployeeDto getEmployee(int id) throws DetailsException;

    EmployeeDto addEmployee(EmployeeDto employeeDto) throws DetailsException;

    EmployeeDto updateEmployee(int empId, EmployeeDto employeeDto) throws DetailsException;

    EmployeeDto deleteEmployee(int id) throws DetailsException;

    List<EmployeeDto> getEmployeeByDeptId(int deptId) throws DetailsException;

}
