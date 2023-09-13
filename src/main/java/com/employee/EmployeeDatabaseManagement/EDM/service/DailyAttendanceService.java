package com.employee.EmployeeDatabaseManagement.EDM.service;

import com.employee.EmployeeDatabaseManagement.EDM.exception.DailyAttendanceNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.*;
import com.employee.EmployeeDatabaseManagement.EDM.repository.DailyAttendanceRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.DepartmentRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DailyAttendanceService {
    private DailyAttendanceRepository dailyAttendanceRepository;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    @Autowired
    public DailyAttendanceService(DailyAttendanceRepository dailyAttendanceRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.dailyAttendanceRepository = dailyAttendanceRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }
    //To validate and render whose attendenc

    public AttendenceList listAll(LocalDate date) {
       try {
           AttendenceList attendenceList=new AttendenceList();
           List<DailyAttendance> list2=dailyAttendanceRepository.findAll().stream().toList();
           List<Employee> list1=employeeRepository.findAll().stream().toList();
           for (Employee employee:list1){
               if (employee.getDOJ().compareTo(date)<=0 && dailyAttendanceRepository.findAll().stream().filter(dailyAttendance -> dailyAttendance.getDate().compareTo(date)==0).collect(Collectors.toList()).isEmpty()){
                       attendenceList.saveDailyAttendanceList(new DailyAttendance(date,employee));
                   }
               }
           return attendenceList;
       }
       catch (Exception e) {
           System.out.println(e);
       }
        return null;
    }

    public DailyAttendance save(DailyAttendance dailyAttendance) {
        return dailyAttendanceRepository.save(dailyAttendance);
    }

    public Department get(String id) {
        return departmentRepository.findById(id).orElseThrow(DailyAttendanceNoSuchElementException::new);
    }

    public DailyAttendance update(String id,DailyAttendance updatedDailyAttendance) {
      DailyAttendance daily= dailyAttendanceRepository.findAll().stream().filter(employee1 -> employee1.getEmployee().getId().equals(id) &&employee1.getDate().compareTo(updatedDailyAttendance.getDate())==0).findFirst().get();
      daily.setMark(updatedDailyAttendance.getMark());
      dailyAttendanceRepository.save(daily);
      return daily;
    }

    public void delete(String  id) {
        dailyAttendanceRepository.deleteById(id);
    }

    public void save(AttendenceList monthly) {
        for (DailyAttendance dailyAttendance: monthly.getDailyAttendanceList()){
            dailyAttendance.setEmployee(employeeRepository.findById(dailyAttendance.getEmployee().getId()).get());
            dailyAttendanceRepository.save(dailyAttendance);
        }
    }
}
