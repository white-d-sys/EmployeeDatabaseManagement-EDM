package com.employee.EmployeeDatabaseManagement.EDM.controller;

import com.employee.EmployeeDatabaseManagement.EDM.exception.PayRollStructNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.exception.UnEmploymentNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.PayrollStruct;
import com.employee.EmployeeDatabaseManagement.EDM.model.UnEmployment;
import com.employee.EmployeeDatabaseManagement.EDM.service.UnEmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/unemployment")
public class UnEmploymentController {
    @Autowired
    private UnEmploymentService unEmploymentService;

    @GetMapping("/retrieve")
    public List<UnEmployment> list() {
        return unEmploymentService.listAll();
    }

    @PostMapping("/create")
    public UnEmployment add(@RequestBody UnEmployment unEmployment) {
        return unEmploymentService.save(unEmployment);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<UnEmployment> get(@PathVariable String id) {
        try {
            UnEmployment unEmployment = unEmploymentService.get(id);
            return ResponseEntity.ok(unEmployment);
        } catch (UnEmploymentNoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UnEmployment> update(@PathVariable String id, @RequestBody UnEmployment unEmployment) {
        try {
            UnEmployment updatedUnEmployment = unEmploymentService.update(id, unEmployment);
            return ResponseEntity.ok(unEmployment);
        } catch (UnEmploymentNoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            unEmploymentService.delete(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (UnEmploymentNoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
