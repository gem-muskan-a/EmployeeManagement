package com.emp.service;

import com.emp.dto.DepartmentDto;
import com.emp.utility.DetailsException;

import java.util.List;

public interface DeptService {
    List<DepartmentDto> getAllDepartment() throws DetailsException;

    DepartmentDto deleteDepartment(int id) throws DetailsException;

    DepartmentDto updateDepartment(int id, DepartmentDto department) throws DetailsException;

    DepartmentDto addDepartment(DepartmentDto department) throws DetailsException;

    DepartmentDto getDepartment(int id) throws DetailsException;
}
