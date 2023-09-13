package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.service.MonthlySalAttenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/employee/monthlyattend")
public class MonthlySalAttenController {
    @Autowired
    private MonthlySalAttenService monthlySalAttenService;

    @GetMapping("/retrieve")
    public String list(@ModelAttribute LocalDate localDate ,Model model) {
        model.addAttribute("SalaryList", monthlySalAttenService.listAll(localDate));
         return "emppage";
    }
    @GetMapping("/employee-salary")
    public  String getSalary(@ModelAttribute Employee employee,Model model){
        model.addAttribute("attendanceList",monthlySalAttenService.listAll(employee));
        return "page";

    }
}
