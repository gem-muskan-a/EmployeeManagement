package com.emp.entity;

import com.emp.dto.DepartmentDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;
    private String departmentName;
    private String departmentDesc;
    private int createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createdOn = new Date();
    private int updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date updatedOn;
    private boolean isActive = true;
    private boolean isDeleted;

    public Department(DepartmentDto dept) {
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
