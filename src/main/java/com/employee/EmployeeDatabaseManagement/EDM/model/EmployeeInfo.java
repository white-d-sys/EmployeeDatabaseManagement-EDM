package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "employee_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfo {
    @MongoId
    private String ID;
    private String address;
    private LocalDate DOB;
    private String learningInstitute;
    private String emContactNumber;
    private String maritualStatus;
    private String spouseName;
    private String prevExp;
    private String currExp;
    private String relationship;
    private String healthCondition;

    public EmployeeInfo(String address,
                        LocalDate DOB,
                        String eearningInstitute,
                        String emContactNumber,
                        String maritualStatus,
                        String spouseName,
                        String prevExp, String currExp,
                        String relationship,
                        String healthCondition) {
        this.address = address;
        this.DOB = DOB;
        this.learningInstitute = eearningInstitute;
        this.emContactNumber = emContactNumber;
        this.maritualStatus = maritualStatus;
        this.spouseName = spouseName;
        this.prevExp = prevExp;
        this.currExp = currExp;
        this.relationship = relationship;
        this.healthCondition = healthCondition;
    }
    private Employee employee;

}
