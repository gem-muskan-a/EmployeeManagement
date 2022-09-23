package com.emp.service;

import com.emp.dto.DepartmentDto;
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
public class DeptServiceImplTest {

    @Mock
    private DepartmentRepository deptRepository;
    @Mock
    private EmployeeRepository empRepo;
    @InjectMocks
    private DeptServiceImpl deptServiceImpl;

    @Test
    @Order(1)
    public void test_getAllDepartment() throws DetailsException {
        Department dept1 = new Department(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), true, false);
        Department dept2 = new Department(2, "MM", "Marketing Managers", 1, new Date(), 1, new Date(), true, false);

        List<Department> expectedResult = new ArrayList<>();
        expectedResult.add(dept1);
        expectedResult.add(dept2);

        when(deptRepository.findAll()).thenReturn(expectedResult);
        assertEquals(expectedResult.size(), deptServiceImpl.getAllDepartment().size());
    }

    @Test
    @Order(2)
    public void test_deleteDepartment() throws DetailsException {

        Department dept = new Department(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), true, false);

        when(deptRepository.findById(any())).thenReturn(Optional.of(dept));
        when(deptRepository.save(any())).thenReturn(dept);
        deptServiceImpl.deleteDepartment(dept.getDepartmentId());
        assertEquals(dept.isDeleted(), true);
    }

    @Test
    @Order(3)
    public void test_addDepartment() throws DetailsException {
        Address address = new Address(1, "GOEL katra", "Gorakhpur", "UttarPradesh", "273152", true, false);
        Employee emp1 = new Employee(1, "MUSKAN", "ASE", 1, "7998671271", address, true, false);

        DepartmentDto dept = new DepartmentDto(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), true, false);

        when(empRepo.findById(any())).thenReturn(Optional.of(emp1));
        when(deptRepository.save(any())).thenReturn(new Department(dept));
        assertEquals(dept, deptServiceImpl.addDepartment(dept));

    }

    @Test
    @Order(4)
    public void test_getDepartmentNotFound() throws DetailsException {
        when(deptRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DetailsException.class, () -> deptServiceImpl.getDepartment(anyInt()));
    }

    @Test
    @Order(5)
    public void test_addDepartmentFailsDueToEmpIdNotExists() throws DetailsException {
        DepartmentDto dept = new DepartmentDto(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), true, false);

        when(empRepo.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DetailsException.class, () -> deptServiceImpl.addDepartment(dept));
    }

    @Test
    @Order(6)
    public void test_updateDepartmentFailsDueTotIdNotExists() throws DetailsException {
        DepartmentDto dept = new DepartmentDto(1, "HR", "Human Resource", 1, new Date(), 1, new Date(), true, false);
        when(empRepo.findById(anyInt())).thenReturn(Optional.empty());
        when(deptRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DetailsException.class, () -> deptServiceImpl.updateDepartment(anyInt(), dept));
    }

    @Test
    @Order(7)
    public void test_deleteDepartmentFailsDueTotIdNotExists() throws DetailsException {
        when(deptRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DetailsException.class, () -> deptServiceImpl.deleteDepartment(anyInt()));
    }
}
