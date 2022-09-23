package com.emp.entity;

import com.emp.dto.EmployeeDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String employeeName;
    private String employeeDesignation;
    private int departmentId;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;
    private boolean isActive = true;
    private boolean isDeleted;

    public Employee(EmployeeDto emp) {
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
