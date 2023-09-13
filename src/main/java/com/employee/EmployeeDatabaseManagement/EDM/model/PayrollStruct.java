package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payroll_struct")
public class PayrollStruct {
    @MongoId
    private String id;
    private Integer baseSalary;
    private Integer HRA;
    private Integer DA;
    private Integer bonus;
    private Integer PF;
    private Integer ESIC;
    @DBRef
    private Department department;
}

