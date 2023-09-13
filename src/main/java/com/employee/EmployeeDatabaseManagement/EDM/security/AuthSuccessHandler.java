package com.employee.EmployeeDatabaseManagement.EDM.security;

import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeCred;
import com.employee.EmployeeDatabaseManagement.EDM.model.user.UserDetail;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeCredRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    EmployeeCredRepository employeeRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String redirectUrl = null;
        String user = authentication.getName();
       String role= employeeRepository.findAll().stream().filter(usr->usr.getUsername().equalsIgnoreCase(user)).map(user1->user1.getRole()).findFirst().get();
       if(role.equalsIgnoreCase("HR"))
            redirectUrl="/app/dashboard";
        else
            redirectUrl="/app/emp-dashboard";
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}
