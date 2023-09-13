package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.exception.MonthlySalAttenNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.*;
import com.employee.EmployeeDatabaseManagement.EDM.service.DailyAttendanceService;
import com.employee.EmployeeDatabaseManagement.EDM.service.MonthlySalAttenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class DailyAttendanceController {
    @Autowired
    private DailyAttendanceService dailyAttendanceService;
    @Autowired
    MonthlySalAttenService monthlySalAttenService;

    @GetMapping("/retrieve")
    public String list(@ModelAttribute Employee employee, Model model) {
         return "page";
    }

    @GetMapping("/create")
    public String add(Model model) {
        model.addAttribute("date",new DailyAttendance());
        return "attendance";
    }
    //SAVE ATTENDANCE IN A PARTICULAR DATE
    @PostMapping("/create-attendence")
    public String addAttendance(@ModelAttribute DailyAttendance dailyAttendance,Model model) {
        AttendenceList list=dailyAttendanceService.listAll(dailyAttendance.getDate());
        model.addAttribute("monthly",dailyAttendanceService.listAll(dailyAttendance.getDate()));
        model.addAttribute("daily",dailyAttendance.getDate().getDayOfWeek());
        return "DailyAttendence";
    }
    //SAVE THE ATTENDANCE

    @PostMapping("/save")
    public String update(@ModelAttribute AttendenceList monthly) {
        dailyAttendanceService.save(monthly);
//        dailyAttendanceService.update(dailyAttendance.getEmployee().getId(), dailyAttendance);
        return "redirect:/app/dashboard";
    }

    @GetMapping("/monthlyreport")
    public String getReport(@RequestParam("date")String date, Model model){
        LocalDate date1=YearMonth.parse(date).atDay(1);
        model.addAttribute("month",date1.getMonth());
        model.addAttribute("n",date1.lengthOfMonth());
        model.addAttribute("attendanceData",monthlySalAttenService.getMonthlyAttendance(date1));
        return "monthly-report";
    }

    @GetMapping("/getsalary")
    public String getSalary(Model model){
        System.out.println("working");
        List<MonthlySalAtten> sal=monthlySalAttenService.listAll().stream().toList();
        System.out.println(sal);
        if (sal.isEmpty()){
            throw  new MonthlySalAttenNoSuchElementException("Salary detail Not Found");
        }
        model.addAttribute("payrollStruct",sal);
        return "emp-monthly-report";
    }
}
