package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeRepository;
import com.employee.EmployeeDatabaseManagement.EDM.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class RegisterLoginController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/login")
    public String getLogin(){
        return "Login";
    }
    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        model.addAttribute("empNo",employeeRepository.findAll().stream().count());
        model.addAttribute("empList",employeeService.getLast());
        return "dashboard";
    }
    @GetMapping("/emp-dashboard")
    public String getDashboardemployee(Model model, HttpServletRequest request){
       String username= request.getUserPrincipal().getName();
      String role=employeeRepository.findAll().stream().filter(employee -> employee.getEmail().equalsIgnoreCase(username)).map(employee -> employee.getDepartment().getDepartment()).findFirst().get();
        model.addAttribute("empNo",employeeRepository.findAll().stream().count());
        model.addAttribute("dep",employeeRepository.findAll().stream().filter(employee -> employee.getDepartment().getDepartment().equalsIgnoreCase(role)).count());
        return "employee_dashboard";
    }

}
