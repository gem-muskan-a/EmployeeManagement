package com.emp.controller;


import com.emp.dto.DepartmentDto;
import com.emp.dto.EmployeeDto;
import com.emp.service.DeptService;
import com.emp.service.EmpService;
import com.emp.utility.DetailsException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class ApiController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private EmpService empService;

    /**
     * API to fetch all department details
     *
     * @return List<department>
     */
    @GetMapping("/department")
    @Operation(summary = "Get all department details")
    public ResponseEntity<List<DepartmentDto>> getDepartments() throws DetailsException {
        return new ResponseEntity<>(this.deptService.getAllDepartment(), HttpStatus.OK);
    }

    /**
     * API to fetch department details by departmentId
     *
     * @param departmentId
     * @return
     */
    @GetMapping("/department/{departmentId}")
    @Operation(summary = "Get department details by departmentId ")
    public ResponseEntity<?> getDepartment(@PathVariable String departmentId) throws DetailsException {
        return new ResponseEntity<>(this.deptService.getDepartment(Integer.parseInt(departmentId)), HttpStatus.OK);
    }

    /**
     * API to add department details
     *
     * @param department
     * @return
     */
    @PostMapping("/department")
    @Operation(summary = "ADD department details")
    public ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentDto department) throws DetailsException {
        log.info("Calling and starting addDepartment()");
        return new ResponseEntity<>(this.deptService.addDepartment(department), HttpStatus.CREATED);
    }

    /**
     * API to update department details
     *
     * @param id
     * @param department
     * @return
     */
    @PutMapping("/department/{id}")
    @Operation(summary = "UPDATE department details")
    public ResponseEntity<?> updateDepartment(@PathVariable int id, @RequestBody DepartmentDto department) throws DetailsException {
        return new ResponseEntity<>(this.deptService.updateDepartment(id, department), HttpStatus.OK);
    }

    /**
     * API to delete department by departmentId
     *
     * @param departmentId
     * @return
     */
    @DeleteMapping("/department/{departmentId}")
    @Operation(summary = "DELETE department details by id")
    public ResponseEntity<?> deleteDepartment(@PathVariable String departmentId) throws DetailsException {
        return new ResponseEntity<>(this.deptService.deleteDepartment(Integer.parseInt(departmentId)), HttpStatus.OK);
    }

    /**
     * API to fetch all employees details
     *
     * @return List<Employees>
     */
    @GetMapping("/employee")
    @Operation(summary = "Get all employees details")
    public ResponseEntity<List<EmployeeDto>> getEmployees() throws DetailsException {
        return new ResponseEntity<>(this.empService.getAllEmployee(), HttpStatus.OK);
    }

    /**
     * API to fetch employee details by DepartmentID
     * <p>
     * param deptId
     *
     * @return List<Employee>
     */
    @GetMapping("/employee/dept/{deptId}")
    @Operation(summary = "Get employee details by departmentId")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDeptId(@PathVariable int deptId) throws DetailsException {
        return new ResponseEntity<>(this.empService.getEmployeeByDeptId(deptId), HttpStatus.OK);
    }

    /**
     * API to fetch employees= details by id
     * <p>
     * param id
     *
     * @return
     */
    @GetMapping("/employee/{id}")
    @Operation(summary = "Get employee details by employeeId")
    public ResponseEntity<?> getEmployee(@PathVariable int id) throws DetailsException {
        return new ResponseEntity<>(this.empService.getEmployee(id), HttpStatus.OK);
    }

    /**
     * API to add employee
     *
     * @param emp
     * @return
     */
    @PostMapping("/employee")
    @Operation(summary = "ADD employee details")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto emp) throws DetailsException {
        return new ResponseEntity<>(this.empService.addEmployee(emp), HttpStatus.CREATED);
    }

    /**
     * API to update employee
     *
     * @param id
     * @param emp
     * @return
     */
    @PutMapping("/employee/{id}")
    @Operation(summary = "UPDATE employee details")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody EmployeeDto emp) throws DetailsException {
        return new ResponseEntity<>(this.empService.updateEmployee(id, emp), HttpStatus.OK);
    }

    /**
     * API to delete employee by id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/employee/{id}")
    @Operation(summary = "DELETE employee details by Id")
    public ResponseEntity<?> deleteEmployee(@Valid @PathVariable int id) throws DetailsException {
        return new ResponseEntity<>(this.empService.deleteEmployee(id), HttpStatus.OK);
    }
}
