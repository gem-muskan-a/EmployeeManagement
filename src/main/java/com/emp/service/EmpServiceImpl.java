package com.emp.service;

import com.emp.dto.EmployeeDto;
import com.emp.entity.Address;
import com.emp.entity.Employee;
import com.emp.repository.DepartmentRepository;
import com.emp.repository.EmployeeRepository;
import com.emp.utility.DetailsException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<EmployeeDto> getAllEmployee() {
        log.info("Inside getAllEmployees()");
        List<EmployeeDto> employees = empRepo.findByIsDeletedIsFalse().stream()
                .map((e) -> this.modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
        if (employees.isEmpty())
            throw new DetailsException("Service.EMPLOYEES_NOT_FOUND");
        log.info("List of employees fetched");
        return employees;
    }

    @Override
    public EmployeeDto getEmployee(int id) {
        log.info("Inside getEmployee(int id)");
        Optional<Employee> emp = empRepo.findById(id);
        Employee e = emp.orElseThrow(() -> new DetailsException("Service.EMPLOYEE_NOT_FOUND"));
        log.info("Employee fetched by id");
        return this.modelMapper.map(e, EmployeeDto.class);
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        log.info("Inside addEmployee(Employee e)");
        Optional opt = Optional.empty();
        if (employeeDto.getDepartmentId() != 0 && deptRepo
                .findById(employeeDto.getDepartmentId()) == opt) {
            throw new DetailsException("Service.DEPARTMENT_NOT_FOUND");
        }
        Employee res = this.modelMapper.map(employeeDto, Employee.class);
        empRepo.save(res);
        log.info("Employee added");
        return this.modelMapper.map(res, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(int empId, EmployeeDto employeeDto) {
        log.info("Inside updateEmployee(int empId, Employee e)");
        Optional<Employee> emp = empRepo.findById(empId);
        Employee employee = emp.orElseThrow(() -> new DetailsException("Service.EMPLOYEE_NOT_FOUND"));
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeeDesignation(employeeDto.getEmployeeDesignation());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setAddress(this.modelMapper.map(employeeDto.getAddress(), Address.class));
        if (!employeeDto.isActive())
            employee.setActive(false);
        empRepo.save(employee);
        log.info("Employee updated");
        return this.modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto deleteEmployee(int id) {
        log.info("Inside deleteEmployee(int id)");
        Optional<Employee> emp = empRepo.findById(id);
        Employee employee = emp.orElseThrow(() ->
                new DetailsException("Service.EMPLOYEE_NOT_FOUND"));
        employee.setActive(false);
        employee.setDeleted(true);
        empRepo.save(employee);
        log.info("Employee deleted");
        return this.modelMapper.map(employee, EmployeeDto.class);
    }


    @Override
    public List<EmployeeDto> getEmployeeByDeptId(int deptId) {
        log.info("Inside getEmployeeByDeptId(int deptId)");
        log.info("Inside getAllEmployees()");
        List<EmployeeDto> employees = empRepo.findByIsDeletedIsFalse().stream()
                .map((e) -> this.modelMapper.map(e, EmployeeDto.class))
                .filter(i -> i.getDepartmentId() == deptId).collect(Collectors.toList());
        log.info("List of employees under given department fetched");
        return employees;
    }
}
