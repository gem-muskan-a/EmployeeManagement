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
    @NotNull(message = "{department.deptName.empty}")
    @Size(min =3,max=25, message = "{department.deptName.size}")
    private String departmentName;
    @NotNull(message = "{department.departmentDesc.empty}")
    @Size(min =3,max=50, message = "{department.departmentDesc.size}")
    private String departmentDesc;
    @NotNull(message = "{department.createdBy.empty}")
    @Min(value = 1, message = "{department.createdBy.empty}")
    private int createdBy;
    private Date createdOn = new Date();
    private int updatedBy;
    private Date updatedOn;
    private boolean isActive = true;
    private boolean isDeleted = false;

}
