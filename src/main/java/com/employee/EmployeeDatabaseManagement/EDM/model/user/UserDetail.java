package com.employee.EmployeeDatabaseManagement.EDM.model.user;

import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeCred;
import com.employee.EmployeeDatabaseManagement.EDM.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDetail implements UserDetails {
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public UserDetail(EmployeeCred employee) {
        this.username = employee.getUsername();
        this.password = employee.getPassword();
        this.authorities = Set.of(new SimpleGrantedAuthority("HR"));
        this.accountNonExpired = true;
        this.accountNonLocked =true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
}
