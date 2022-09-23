package com.emp.service;

import com.emp.dto.DepartmentDto;
import com.emp.entity.Department;
import com.emp.repository.DepartmentRepository;
import com.emp.repository.EmployeeRepository;
import com.emp.utility.DetailsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DepartmentRepository deptRepo;

    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public List<DepartmentDto> getAllDepartment() throws DetailsException {
        log.info("Inside getAllDepartment()");
        List<DepartmentDto> departments = deptRepo.findAll().stream().map((d) -> new DepartmentDto(d)).filter(i -> !i.isDeleted()).collect(Collectors.toList());
        if(departments.isEmpty())
            throw new DetailsException("Service.DEPARTMENTS_NOT_FOUND");
        log.info("Method successful");
        return departments;
    }

    @Override
    public DepartmentDto deleteDepartment(int id) throws DetailsException {
        log.info("Inside deleteDepartment(int id)");
        Optional<Department> c = deptRepo.findById(id);
        Department dept = c.orElseThrow(() -> new DetailsException("Service.DEPARTMENTS_NOT_FOUND"));
        dept.setDeleted(true);
        dept.setActive(false);
        deptRepo.save(dept);
        log.info("Method successful");
        return new DepartmentDto(dept);
    }

    @Override
    public DepartmentDto updateDepartment(int id, DepartmentDto department) throws DetailsException {
        log.info("Inside updateDepartment(int id, Department d)");
        Optional opt = Optional.empty();
        if(empRepo.findById(department.getUpdatedBy()) == opt){
            throw new DetailsException("Employing updating the department not found");
        }
        Optional<Department> c = deptRepo.findById(id);
        Department dept = c.orElseThrow(() -> new DetailsException("Service.DEPARTMENT_NOT_FOUND"));
        dept.setDepartmentName(department.getDepartmentName());
        dept.setDepartmentDesc(department.getDepartmentDesc());
        if (!department.isActive()) dept.setActive(false);
        dept.setUpdatedBy(department.getUpdatedBy());
        dept.setUpdatedOn(new Date());
        deptRepo.save(dept);
        log.info("Method successful");
        return new DepartmentDto(dept);
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto department) throws DetailsException{
        log.info("Inside addDepartment(Department d)");
        Optional opt = Optional.empty();
        if(empRepo.findById(department.getCreatedBy()) == opt){
            throw new DetailsException("Employing creating the department not found");
        }
        deptRepo.save(new Department(department));
        log.info("Method successful");
        return department;
    }

    @Override
    public DepartmentDto getDepartment(int id) throws DetailsException {
        log.info("Inside getDepartment(int id)");
        Optional<Department> dept = deptRepo.findById(id);
        Department department = dept.orElseThrow(() -> new DetailsException("Service.DEPARTMENT_NOT_FOUND"));
        log.info("Method successful");
        return new DepartmentDto(department);
    }
}
