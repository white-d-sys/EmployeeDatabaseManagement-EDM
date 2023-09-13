package com.employee.EmployeeDatabaseManagement.EDM.model;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum RolePermission {

    HR_READ("hr:read"),
    HR_UPDATE("hr:update"),
    HR_CREATE("hr:create"),
    HR_DELETE("hr:delete"),

    MANAGER_READ("manager:read"),
    MANAGER_UPDATE("manager:update"),
    MANAGER_CREATE("manager:create"),
    MANAGER_DELETE("manager:delete"),

    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_UPDATE("employee:read"),
    EMPLOYEE_CREATE("employee:read"),
    EMPLOYEE_DELETE("employee:read");

    private final String permission;
    public String getPermission() {
        return permission;
    }

}

