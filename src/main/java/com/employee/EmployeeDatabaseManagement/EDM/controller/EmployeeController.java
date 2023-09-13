package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.exception.DailyAttendanceNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.Department;
import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeInfo;
import com.employee.EmployeeDatabaseManagement.EDM.model.user.UserDetail;
import com.employee.EmployeeDatabaseManagement.EDM.repository.DepartmentRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeCredRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeRepository;
import com.employee.EmployeeDatabaseManagement.EDM.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeCredRepository employeeCredRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/retrieve")
    public List<Employee> list() {
        return employeeService.listAll();
    }
    @PostMapping("/employee-create")
    public String registerEmployee(@ModelAttribute Department department, Model model){
            Employee employee1=new Employee();
            employee1.setDepartment(departmentRepository.findAll().stream().filter(department1 -> department1.getDepartment().equalsIgnoreCase(department.getDepartment())).findFirst().get());
            employee1.setId(employeeService.getId(department.getDepartment()));
            model.addAttribute("employee",employee1);
            return "add-employee";
    }

    @PostMapping("/create")
    public String add(@ModelAttribute Employee employee, Model model) {
        System.out.println(employee);
       employee.setDepartment(departmentRepository.findById(employee.getDepartment().getId()).get());
        employeeService.update(employee);
        EmployeeInfo employeeInfo=new EmployeeInfo();
        employeeInfo.setEmployee(employee);
        model.addAttribute("employeeinfo",employeeInfo);
        return  "employee-info";
    }

    @GetMapping("/retrieve")
    public String get(@ModelAttribute String id,Model model,HttpServletRequest request) {
        String user=request.getUserPrincipal().getName();
        String role=employeeCredRepository.findAll().stream().filter(employeeCred -> employeeCred.getUsername().equalsIgnoreCase(user))
                .map(employeeCred ->employeeCred.getRole() ).findFirst().get();
        if (role.equalsIgnoreCase("HR")){
            model.addAttribute("employees",employeeService.listAll());
            return "employee-view";
        }
        role=employeeRepository.findAll().stream().filter(employeeCred ->employeeCred.getEmail().equalsIgnoreCase(user)).map(employee -> employee.getDepartment().getDepartment()).findFirst().get();
        String finalRole = role;
        model.addAttribute("employees",employeeService.listAll().stream().filter(employee -> employee.getDepartment().getDepartment().equalsIgnoreCase(finalRole)).toList());
        return "employee-view";
    }

    @GetMapping("/view")
    public String delete(HttpServletRequest request, Model model) {
        System.out.println(request.getUserPrincipal().getName());
       Employee employee1=employeeRepository.findAll().stream().filter(employee -> employee.getEmail().equalsIgnoreCase(request.getUserPrincipal().getName())).findFirst().get();
        model.addAttribute("employee",employee1);
        return "profile";

    }
}
