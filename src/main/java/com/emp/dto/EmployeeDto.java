package com.emp.dto;

import com.emp.entity.Address;
import com.emp.entity.Employee;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private int employeeId;
    @NotEmpty(message ="{employee.name.empty}")
    @NotBlank(message = "{employee.name.empty}")
    private String employeeName;
    @NotEmpty(message = "{employee.designation.empty}")
    @NotBlank(message = "{employee.designation.empty}")
    private String employeeDesignation;
    private int departmentId;
    @NotEmpty(message = "{employee.phoneNumber.empty}")
    @NotBlank(message = "{employee.phoneNumber.empty}")
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;
    private Address address;
    private boolean isActive = true;
    private boolean isDeleted;

    public EmployeeDto(Employee emp) {
        this.employeeId = emp.getEmployeeId();
        this.departmentId = emp.getDepartmentId();
        this.employeeDesignation = emp.getEmployeeDesignation();
        this.employeeName = emp.getEmployeeName();
        this.phoneNumber = emp.getPhoneNumber();
        this.address = emp.getAddress();
        this.isActive = emp.isActive();
        this.isDeleted = emp.isDeleted();
    }
}
