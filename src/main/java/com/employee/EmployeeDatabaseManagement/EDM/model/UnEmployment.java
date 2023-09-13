package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "unemployment")
public class UnEmployment {
    @MongoId
    private String ID;
    private String name;
    private String email;
    private String number;
    private LocalDate DOJ;
    private LocalDate DOL;
    private String aadharNo;
    private String panNO;
    private String benfits;
    @DBRef
    private EmployeeInfo employeeInfo;
    @DBRef
    private Department department;

    public UnEmployment(Employee employee, String Benfits) {
        this.ID = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.number = employee.getEmail();
        this.DOJ = employee.getDOJ();
        this.DOL = employee.getDOL();
        this.aadharNo = employee.getAadharNo();
        this.panNO = employee.getPanNo();
        this.benfits = Benfits;
        this.employeeInfo = employee.getEmployeeInfo();
        this.department = employee.getDepartment();
    }
}