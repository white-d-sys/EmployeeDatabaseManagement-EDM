package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.exception.PayRollStructNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.Department;
import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.model.PayrollStruct;
import com.employee.EmployeeDatabaseManagement.EDM.repository.DepartmentRepository;
import com.employee.EmployeeDatabaseManagement.EDM.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/app")
public class PayrollStructController {
    DepartmentRepository departmentRepository;
    DepartmentService departmentService;
    @Autowired

    public PayrollStructController(DepartmentRepository departmentRepository,  DepartmentService departmentService) {
        this.departmentRepository = departmentRepository;
        this.departmentService = departmentService;
    }

    @GetMapping("/retrieve")
    public String list(Model model)
    {
        System.out.println(departmentService.listAll());
        model.addAttribute("departments",departmentService.listAll());
        return "dep-view";
    }
    @GetMapping("/salary/retrieve")
    public String listSalary(Model model)
    {
        model.addAttribute("employees",departmentService.listAll());
        return "payroll-detail";
    }

    @GetMapping("/create-department")
    public String add(Model model) {
        Department department=new Department();
        department.setId(departmentService.getId());
        System.out.println(departmentService.getId());
        model.addAttribute("department",department);
//        departmentService.savePayroll(payrollStruct);
        return "department";
    }
    @GetMapping("/create")
    public String addEmployee(Model model){
        model.addAttribute("departments", departmentService.listAll());
        return "add-emp-dept";
    }
    @PostMapping("/department")
    public String addDepartment(@ModelAttribute Department department,Model model) {
        departmentService.save(department);
        model.addAttribute("payrollstruct",department);
        return "payroll-struct";
    }
    @GetMapping("/salary/update")
    public String updateDepartment(@PathVariable Department department) {
        System.out.println(department);
//        departmentService.savePayroll(payrollStruct);
        return "update-department";
    }
    @GetMapping("/department/update/{id}")
    public String updateSalary(@PathVariable String id,Model model) {
        System.out.println(id);
        model.addAttribute("payrollstruct",departmentService.findDepartment(id));
        return "payroll-struct";
    }
    @PostMapping("/salary")
    public String addSalary(@ModelAttribute Department payrollStruct,Model model) {
        departmentService.setPayrollStruct(payrollStruct.getId(),payrollStruct);
        model.addAttribute("employees",departmentService.listAll());
        return "payroll-detail";
    }

    @GetMapping("/retrieve/{id}")
    public String get(@ModelAttribute Department department) {
        departmentService.getPayrollStruct(department);
        return "page";
    }

    @GetMapping("/department/retrieve")
    public String getDepartment(Model model) {
        model.addAttribute("department",departmentService.getDepartment());
        return "dep-view";
    }
}
