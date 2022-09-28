package com.emp.service;

import com.emp.dto.AddressDto;
import com.emp.dto.EmployeeDto;
import com.emp.entity.Address;
import com.emp.entity.Department;
import com.emp.entity.Employee;
import com.emp.repository.DepartmentRepository;
import com.emp.repository.EmployeeRepository;
import com.emp.utility.DetailsException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmpServiceImplTest {

    @Mock
    private DepartmentRepository deptRepository;
    @Mock
    private EmployeeRepository empRepo;
    @InjectMocks
    private EmpServiceImpl empServiceImpl;

    ModelMapper modelMapper=new ModelMapper();

    @Test
    @Order(1)
    public void test_getAllEmployees() throws DetailsException {
        List<Employee> expectedResult = new ArrayList<>();
        Address address = new Address(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        Employee emp1 = new Employee(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);
        Address address2 = new Address(2, "Pipraich", "Gorakhpur", "UttarPradesh", "273152", true, false);
        Employee emp2 = new Employee(2, "MUSKAN", "ASE", 1, "7998671271", address2, true, false);

        expectedResult.add(emp1);
        expectedResult.add(emp2);

        when(empRepo.findByIsDeletedIsFalse()).thenReturn(expectedResult);
        assertEquals(2, empServiceImpl.getAllEmployee().size());
    }

    @Test
    @Order(2)
    public void test_addEmployee() throws DetailsException {
        AddressDto address = new AddressDto(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp1 = new EmployeeDto(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        Employee emp=modelMapper.map(emp1,Employee.class);
        Department dept = new Department(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), true, false);

        when(deptRepository.findById(any())).thenReturn(Optional.of(dept));
        when(empRepo.save(emp)).thenReturn(emp);
        assertEquals(modelMapper.map(emp,EmployeeDto.class).getEmployeeId(), empServiceImpl.addEmployee(emp1).getEmployeeId());
    }

    @Test
    @Order(3)
    public void test_deleteEmployee() throws DetailsException {
        Address address = new Address(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        Employee emp1 = new Employee(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        when(empRepo.findById(any())).thenReturn(Optional.of(emp1));
        when(empRepo.save(any())).thenReturn(emp1);
        empServiceImpl.deleteEmployee(emp1.getEmployeeId());
        assertEquals(true,emp1.isDeleted());
    }

    @Test
    @Order(4)
    public void test_getEmployeeByDeptId() throws DetailsException {
        List<Employee> expectedResult = new ArrayList<>();

        Address address = new Address(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        Employee emp1 = new Employee(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        Address address2 = new Address(2, "Pipraich", "Gorakhpur", "UttarPradesh", "273152", true, false);
        Employee emp2 = new Employee(2, "MUSKAN", "ASE", 2, "7998671271", address2, true, false);

        expectedResult.add(emp1);
        expectedResult.add(emp2);

        Department dept = new Department(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), true, false);

        when(empRepo.findByIsDeletedIsFalse()).thenReturn(expectedResult);
        assertEquals(1, empServiceImpl.getEmployeeByDeptId(dept.getDepartmentId()).size());
    }

    @Test
    @Order(5)
    public void test_getEmployeeNotFound() throws DetailsException {
        when(empRepo.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DetailsException.class, () -> empServiceImpl.getEmployee(anyInt()));
    }

    @Test
    @Order(6)
    public void test_addEmployeeFailsDueToDeptIdNotExists() throws DetailsException {
        AddressDto address = new AddressDto(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp1 = new EmployeeDto(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        when(deptRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DetailsException.class, () -> empServiceImpl.addEmployee(emp1));
    }

    @Test
    @Order(7)
    public void test_updateEmployeeFailsDueTotIdNotExists() throws DetailsException {
        AddressDto address = new AddressDto(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto emp1 = new EmployeeDto(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        when(empRepo.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DetailsException.class, () -> empServiceImpl.updateEmployee(anyInt(), emp1));
    }

    @Test
    @Order(8)
    public void test_deleteEmployeeFailsDueTotIdNotExists() throws DetailsException {
        when(empRepo.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DetailsException.class, () -> empServiceImpl.deleteEmployee(anyInt()));
    }

    @Test
    @Order(9)
    public void test_updateEmployee() {
        Address address = new Address(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        Employee emp1 = new Employee(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        AddressDto address1 = new AddressDto(1, "GOEL katra near", "Gorakhpur", "UttarPradesh", "273152", true, false);
        EmployeeDto empUpdate = new EmployeeDto(1, "MUSKAN Agrawal", "ASE", 1, "7998671271", address1, false, false);

        when(empRepo.findById(any())).thenReturn(Optional.of(emp1));
        when(empRepo.save(any())).thenReturn(emp1);
        assertEquals(empUpdate.getEmployeeName(), empServiceImpl.updateEmployee(empUpdate.getEmployeeId(), empUpdate).getEmployeeName());

    }

    @Test
    @Order(10)
    public void test_getEmployee() {
        Address address = new Address(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        Employee emp1 = new Employee(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        when(empRepo.findById(any())).thenReturn(Optional.of(emp1));
        assertEquals(emp1.getEmployeeName(), empServiceImpl.getEmployee(1).getEmployeeName());
    }
}



