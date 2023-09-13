package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.exception.EmployeeInfoNoSuchException;
import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeCred;
import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeInfo;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeRepository;
import com.employee.EmployeeDatabaseManagement.EDM.service.EmployeeCredService;
import com.employee.EmployeeDatabaseManagement.EDM.service.EmployeeInfoService;
import com.employee.EmployeeDatabaseManagement.EDM.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employeeinfo")
public class EmployeeInfoController {
    private EmployeeInfoService employeeInfoService;
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeCredService employeeCredService;

    @Autowired
    public EmployeeInfoController(EmployeeInfoService employeeInfoService, EmployeeService employeeService, EmployeeCredService employeeCredService) {
        this.employeeInfoService = employeeInfoService;
        this.employeeService = employeeService;
        this.employeeCredService = employeeCredService;
    }

    @GetMapping("/retrieve")
    public List<EmployeeInfo> list() {
        return employeeInfoService.listAll();
    }

    @PostMapping("/create")
    public String add( @ModelAttribute  EmployeeInfo employeeInfo, Model model) {
       Employee employee= employeeService.get(employeeInfo.getEmployee().getId());
        employeeInfoService.save(employeeInfo);
        employee.setEmployeeInfo(employeeInfo);
        employeeService.save(employee);
        EmployeeCred employeeCred=new EmployeeCred();
        employeeCred.setEmployee(employee);
        employeeCred.setUsername(employee.getEmail());
        model.addAttribute("employeecred",employeeCred);
        return "password-set";
    }
    @PostMapping("/save-password")
    public String setPassword(@ModelAttribute EmployeeCred employeeCred){
        System.out.println(employeeCred.getUsername());
       Employee employee1= employeeRepository.findAll().stream().filter(employee -> employee.getEmail().equalsIgnoreCase(employeeCred.getUsername())).findFirst().get();
       employee1.setEmployeeCred(employeeCred);
       employeeCredService.save(employeeCred);
        employeeService.save(employee1);
        return "dashboard";
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<EmployeeInfo> get(@PathVariable String id) {
        try {
            EmployeeInfo employeeInfo = employeeInfoService.get(id);
            return ResponseEntity.ok(employeeInfo);
        } catch (EmployeeInfoNoSuchException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeInfo> update(@PathVariable String id, @RequestBody EmployeeInfo employeeInfo) {
        try {
            EmployeeInfo updatedEmployeeInfo = employeeInfoService.update(id, employeeInfo);
            return ResponseEntity.ok(updatedEmployeeInfo);
        } catch (EmployeeInfoNoSuchException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            employeeInfoService.delete(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (EmployeeInfoNoSuchException e) {
            return ResponseEntity.notFound().build();
        }
    }



}
