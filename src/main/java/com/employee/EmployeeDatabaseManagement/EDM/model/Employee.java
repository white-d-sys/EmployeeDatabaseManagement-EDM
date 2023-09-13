package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection ="employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    private String id;
    private String name;
    private String gender;
    private Integer hike;
    @Indexed(unique = true)
    private String email;
    private String number;
    private LocalDate DOJ;
    private LocalDate DOL;
    private String aadharNo;
    private String panNo;

    public Employee( String name, String gender, Integer hike, String email, String number, LocalDate DOJ, LocalDate DOL, String aadharNo, String panNo) {
        this.name = name;
        this.gender = gender;
        this.hike = hike;
        this.email = email;
        this.number = number;
        this.DOJ = DOJ;
        this.DOL = DOL;
        this.aadharNo = aadharNo;
        this.panNo = panNo;
    }

    @DBRef
    private EmployeeInfo employeeInfo;
    @DBRef
    private EmployeeCred employeeCred;
    @DBRef
    private Department department;
    @DBRef
    private DailyAttendance dailyAttendance;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", hike=" + hike +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", DOJ=" + DOJ +
                ", DOL=" + DOL +
                ", aadharNo='" + aadharNo + '\'' +
                ", panNo='" + panNo + '\'' +
                ", employeeInfo=" + employeeInfo +
                ", employeeCred=" + employeeCred +
                ", department=" + department +
                ", dailyAttendance=" + dailyAttendance +
                '}';
    }
}
