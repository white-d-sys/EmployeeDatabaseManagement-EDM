package com.employee.EmployeeDatabaseManagement.EDM.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MonthlySalAttenNoSuchElementException extends RuntimeException{
    private String message;
}
