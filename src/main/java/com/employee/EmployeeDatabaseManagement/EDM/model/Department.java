package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "department")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Department {
    @MongoId
    private String id;
    private String Department;
    @DBRef
    private PayrollStruct payrollStruct;

    public Department(String department) {
        Department = department;
    }
}
