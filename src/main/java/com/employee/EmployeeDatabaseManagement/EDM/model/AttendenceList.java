package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
//@NoArgsConstructor
public class AttendenceList {
    List<DailyAttendance> dailyAttendanceList;

    public AttendenceList() {
        dailyAttendanceList=new ArrayList<>();
    }

    public void saveDailyAttendanceList(DailyAttendance dailyAttendanceList) {
        this.dailyAttendanceList.add(dailyAttendanceList);
    }

    public List<DailyAttendance> getDailyAttendanceList() {
        return dailyAttendanceList;
    }

    public void setDailyAttendanceList(List<DailyAttendance> dailyAttendanceList) {
        this.dailyAttendanceList = dailyAttendanceList;
    }
}
