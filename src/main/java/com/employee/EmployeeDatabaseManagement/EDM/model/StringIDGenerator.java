package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StringIDGenerator
{
    @MongoId
    private String id;
    private Integer employee;
    private Integer department;

    public StringIDGenerator(Integer employee, Integer department) {
        this.employee = employee;
        this.department = department;
    }

}
