package com.emp.service;

import com.emp.dto.EmployeeDto;
import com.emp.entity.Employee;
import com.emp.repository.DepartmentRepository;
import com.emp.repository.EmployeeRepository;
import com.emp.utility.DetailsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private DepartmentRepository deptRepo;

    @Override
    public List<EmployeeDto> getAllEmployee() throws DetailsException {
        log.info("Inside getAllEmployees()");
        List<EmployeeDto> employees = empRepo.findAll().stream()
                .map((e) -> new EmployeeDto(e))
                .filter(i -> !i.isDeleted()).collect(Collectors.toList());
        if (employees.isEmpty())
            throw new DetailsException("Service.EMPLOYEES_NOT_FOUND");
        log.info("Method successful");
        return employees;
    }

    @Override
    public EmployeeDto getEmployee(int id) throws DetailsException {
        log.info("Inside getEmployee(int id)");
        Optional<Employee> emp = empRepo.findById(id);
        Employee e = emp.orElseThrow(() -> new DetailsException("Service.EMPLOYEE_NOT_FOUND"));
        log.info("Method successful");
        return new EmployeeDto(e);
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws DetailsException {
        log.info("Inside addEmployee(Employee e)");
        Optional opt = Optional.empty();
        if (employeeDto.getDepartmentId() != 0 && deptRepo
                .findById(employeeDto.getDepartmentId()) == opt) {
            throw new DetailsException("Service.DEPARTMENT_NOT_FOUND");
        }
        empRepo.save(new Employee(employeeDto));
        log.info("Method successful");
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(int empId, EmployeeDto employeeDto) throws DetailsException {
        log.info("Inside updateEmployee(int empId, Employee e)");
        Optional<Employee> emp = empRepo.findById(empId);
        Employee employee = emp.orElseThrow(() -> new DetailsException("Service.EMPLOYEE_NOT_FOUND"));
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeeDesignation(employeeDto.getEmployeeDesignation());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setAddress(employeeDto.getAddress());
        if (!employeeDto.isActive())
            employee.setActive(false);
        empRepo.save(employee);
        log.info("Method successful");
        return new EmployeeDto(employee);
    }

    @Override
    public EmployeeDto deleteEmployee(int id) throws DetailsException {
        log.info("Inside deleteEmployee(int id)");
        Optional<Employee> emp = empRepo.findById(id);
        Employee employee = emp.orElseThrow(() ->
                new DetailsException("Service.EMPLOYEE_NOT_FOUND"));
        employee.setActive(false);
        employee.setDeleted(true);
        empRepo.save(employee);
        log.info("Method successful");
        return new EmployeeDto(employee);
    }


    @Override
    public List<EmployeeDto> getEmployeeByDeptId(int deptId) throws DetailsException {
        log.info("Inside getEmployeeByDeptId(int deptId)");
        log.info("Inside getAllEmployees()");
        List<EmployeeDto> employees = empRepo.findAll().stream()
                .map((e) -> new EmployeeDto(e))
                .filter(i -> i.getDepartmentId() == deptId).collect(Collectors.toList());
        log.info("Method successful");
        return employees;
    }
}
