package com.emp.service;

import com.emp.dto.DepartmentDto;
import com.emp.utility.DetailsException;

import java.util.List;

public interface DeptService {
    List<DepartmentDto> getAllDepartment();

    DepartmentDto deleteDepartment(int id);

    DepartmentDto updateDepartment(int id, DepartmentDto department);

    DepartmentDto addDepartment(DepartmentDto department);

    DepartmentDto getDepartment(int id);
}
