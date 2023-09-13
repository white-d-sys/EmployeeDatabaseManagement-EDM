package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Set;

import static com.employee.EmployeeDatabaseManagement.EDM.model.RolePermission.*;

@RequiredArgsConstructor
public enum UserRole {

    HR(Set.of(
            HR_READ,
            HR_UPDATE,
            HR_CREATE,
            HR_DELETE,

            MANAGER_READ,
            MANAGER_UPDATE,
            MANAGER_CREATE,
            MANAGER_DELETE,

            EMPLOYEE_READ,
            EMPLOYEE_UPDATE,
            EMPLOYEE_CREATE,
            EMPLOYEE_DELETE
    )),
    Manager(Set.of(
            MANAGER_READ,
            MANAGER_UPDATE,
            MANAGER_CREATE,
            MANAGER_DELETE
    )),
    Employee(Set.of(EMPLOYEE_READ,
            EMPLOYEE_UPDATE,
            EMPLOYEE_CREATE,
            EMPLOYEE_DELETE));
    @Getter
    private final Set<RolePermission> permissions;
    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities=getPermissions()
                .stream()
                .map(permission->new SimpleGrantedAuthority(permission.name()))
                .toList();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;

    }
}