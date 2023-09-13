package com.employee.EmployeeDatabaseManagement.EDM.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DailyAttendanceNoSuchElementException extends RuntimeException{
    private String message;
}
