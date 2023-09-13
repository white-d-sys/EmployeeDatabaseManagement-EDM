package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;



@Document(collection = "daily_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyAttendance {
    @Id
    private String id;
    private LocalDate date;
    private AttendanceMark mark;
    @DBRef
    private Employee employee;

    public DailyAttendance(LocalDate date, AttendanceMark mark, Employee employee) {
        this.id = id;
        this.date = date;
        this.mark = mark;
        this.employee = employee;
    }

    public DailyAttendance(LocalDate date, Employee employee) {
        this.date = date;
        this.employee = employee;
    }
}

