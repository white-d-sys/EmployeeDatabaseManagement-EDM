package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.time.Month;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Monthly_Attendance")
public class MonthlySalAtten {
    @MongoId
    private String ID;
    private LocalDate month;
    private Integer monthlyPresent;
    private Integer paidleave;
    private Integer unpaidLeave;
    private  Integer salary;

    public MonthlySalAtten(LocalDate month, Integer monthlyPresent, Integer paidleave, Integer unpaidLeave, Integer salary, Employee employee) {
        this.month = month;
        this.monthlyPresent = monthlyPresent;
        this.paidleave = paidleave;
        this.unpaidLeave = unpaidLeave;
        this.salary = salary;
        this.employee = employee;
    }

    @DBRef
    private Employee employee;
}

