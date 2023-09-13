package com.employee.EmployeeDatabaseManagement.EDM.service;

import com.employee.EmployeeDatabaseManagement.EDM.exception.DailyAttendanceNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.exception.EmployeeInfoNoSuchException;
import com.employee.EmployeeDatabaseManagement.EDM.model.DailyAttendance;
import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeInfo;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeInfoService {

    @Autowired
    private EmployeeInfoRepository employeeInfoRepository;

    public List<EmployeeInfo> listAll() {
        return employeeInfoRepository.findAll();
    }

    public EmployeeInfo save(EmployeeInfo employeeInfo) {
        return employeeInfoRepository.save(employeeInfo);
    }

    public EmployeeInfo get(String id) {
        return employeeInfoRepository.findById(id).orElseThrow(EmployeeInfoNoSuchException::new);
    }

    public EmployeeInfo update(String id, EmployeeInfo updatedEmployeeInfo) {
        EmployeeInfo existingEmployeeInfo = get(id);
        existingEmployeeInfo.setAddress(updatedEmployeeInfo.getAddress());
        existingEmployeeInfo.setDOB(updatedEmployeeInfo.getDOB());
//        existingEmployeeInfo.setLearningInstitute(updatedEmployeeInfo.getLearningInstitute());
        existingEmployeeInfo.setEmContactNumber(updatedEmployeeInfo.getEmContactNumber());
        existingEmployeeInfo.setMaritualStatus(updatedEmployeeInfo.getMaritualStatus());
        existingEmployeeInfo.setSpouseName(updatedEmployeeInfo.getSpouseName());
        existingEmployeeInfo.setRelationship(updatedEmployeeInfo.getRelationship());
        existingEmployeeInfo.setHealthCondition(updatedEmployeeInfo.getHealthCondition());

        return employeeInfoRepository.save(existingEmployeeInfo);
    }

    public void delete(Long id) {
        employeeInfoRepository.deleteById(String.valueOf(id));
    }
}


