package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class AttendanceData {
    List<Map<String, Map<List<LocalDate>, List<AttendanceMark>>>> attendanceData;

    public AttendanceData() {
        this.attendanceData = new ArrayList<>();
    }
}
