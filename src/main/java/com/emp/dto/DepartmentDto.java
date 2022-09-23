package com.emp.dto;

import com.emp.entity.Department;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {
    private int departmentId;
    @NotEmpty(message = "{department.deptName.empty}")
    @NotBlank(message = "{department.deptName.empty}")
    private String departmentName;
    @NotEmpty(message = "{department.departmentDesc.empty}")
    @NotBlank(message = "{department.departmentDesc.empty}")
    private String departmentDesc;
    @NotNull(message = "{department.createdBy.empty}")
    @Min(value = 1, message = "{department.createdBy.empty}")
    private int createdBy;
    private Date createdOn = new Date();
    private int updatedBy;
    private Date updatedOn;
    private boolean isActive = true;
    private boolean isDeleted;

    public DepartmentDto(Department dept) {
        this.departmentId = dept.getDepartmentId();
        this.departmentName = dept.getDepartmentName();
        this.departmentDesc = dept.getDepartmentDesc();
        this.createdBy = dept.getCreatedBy();
        this.updatedBy = dept.getUpdatedBy();
        this.createdOn = dept.getCreatedOn();
        this.updatedOn = dept.getUpdatedOn();
        this.isActive = dept.isActive();
        this.isDeleted = dept.isDeleted();
    }
}
