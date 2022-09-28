package com.emp.dto;

import com.emp.entity.Employee;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private int employeeId;
    @NotNull(message = "{employee.name.empty}")
    @Size(min =3,max=25, message = "{employee.name.size}")
    private String employeeName;
    @NotNull(message = "{employee.designation.empty}")
    @Size(min =3,max=25, message = "{employee.designation.size}")
    private String employeeDesignation;
    private int departmentId;
    @NotNull(message = "{employee.phoneNumber.empty}")
    @Size(message = "{employee.phoneNumber.size}")
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;
    private AddressDto address;
    private boolean isActive = true;
    private boolean isDeleted = false;

}
