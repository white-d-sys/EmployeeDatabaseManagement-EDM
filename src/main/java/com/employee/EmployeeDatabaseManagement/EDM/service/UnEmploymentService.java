package com.employee.EmployeeDatabaseManagement.EDM.service;

import com.employee.EmployeeDatabaseManagement.EDM.exception.PayRollStructNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.exception.UnEmploymentNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.PayrollStruct;
import com.employee.EmployeeDatabaseManagement.EDM.model.UnEmployment;
import com.employee.EmployeeDatabaseManagement.EDM.repository.UnEmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UnEmploymentService {
    @Autowired
    private UnEmploymentRepository unEmploymentRepository;

    public List<UnEmployment> listAll() {
        return unEmploymentRepository.findAll();
    }

    public UnEmployment save(UnEmployment unEmployment) {
        return unEmploymentRepository.save(unEmployment);
    }

    public UnEmployment get(String id) {
        return unEmploymentRepository.findById(id).orElseThrow(UnEmploymentNoSuchElementException::new);
    }

    public UnEmployment update(String id, UnEmployment updateUnEmployment) {
        UnEmployment existingUnEmployment = get(id);
        existingUnEmployment.setName(updateUnEmployment.getName());
        existingUnEmployment.setEmail(updateUnEmployment.getEmail());
        existingUnEmployment.setNumber(updateUnEmployment.getNumber());
        existingUnEmployment.setDOJ(updateUnEmployment.getDOJ());
        existingUnEmployment.setDOL(updateUnEmployment.getDOL());
        existingUnEmployment.setAadharNo(updateUnEmployment.getAadharNo());
        existingUnEmployment.setPanNO(updateUnEmployment.getPanNO());
        existingUnEmployment.setBenfits(updateUnEmployment.getBenfits());
        return unEmploymentRepository.save(existingUnEmployment);
    }

    public void delete(Long id) {
        unEmploymentRepository.deleteById(String.valueOf(id));
    }
}
