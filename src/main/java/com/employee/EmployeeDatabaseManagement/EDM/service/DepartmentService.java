package com.employee.EmployeeDatabaseManagement.EDM.service;

import com.employee.EmployeeDatabaseManagement.EDM.exception.DepartmentNoSuchElementException;
import com.employee.EmployeeDatabaseManagement.EDM.model.Department;
import com.employee.EmployeeDatabaseManagement.EDM.model.PayrollStruct;
import com.employee.EmployeeDatabaseManagement.EDM.model.StringIDGenerator;
import com.employee.EmployeeDatabaseManagement.EDM.repository.DepartmentRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.IDGeneratorRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.PayrollStructRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentService {
     DepartmentRepository departmentRepository;
    PayrollStructRepository payrollStructRepository;
    IDGeneratorRepository idGeneratorRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, IDGeneratorRepository idGeneratorRepository,PayrollStructRepository payrollStructRepository) {
        this.departmentRepository = departmentRepository;
        this.payrollStructRepository = payrollStructRepository;
        this.idGeneratorRepository=idGeneratorRepository;
    }

    public List<Department> listAll() {
        return departmentRepository.findAll();
    }
    public String getId(){
        if (idGeneratorRepository.findAll().isEmpty()){
            idGeneratorRepository.save(new StringIDGenerator(10001,10001));
            return "PDDEPT10001";
        }
        String id="PD"+"DEPT"+idGeneratorRepository.findAll().get(0).getDepartment();
        return id;
    }

    public Department save(Department department) {
        StringIDGenerator id;
        if (idGeneratorRepository.findAll().isEmpty()){
            idGeneratorRepository.save(new StringIDGenerator(100,1000));
            id=idGeneratorRepository.findAll().stream().findFirst().get();
            department.setId("PD-DEPT-"+id.getDepartment());
            id.setDepartment(id.getDepartment()+1);
            idGeneratorRepository.save(id);
            return  departmentRepository.save(department);
        }
        id=idGeneratorRepository.findAll().stream().findFirst().get();
        department.setId("PD-DEPT-"+id.getDepartment());
        id.setDepartment(id.getDepartment()+1);
        idGeneratorRepository.save(id);
        System.out.println("working till here");
        return departmentRepository.save(department);
    }
    public boolean setPayrollStruct(String id,Department department){
        department.setDepartment(departmentRepository.findById(id).get().getDepartment());
        System.out.println(department);
        payrollStructRepository.save(department.getPayrollStruct());
        departmentRepository.save(department);
        return true;
    }
    public List<PayrollStruct> savePayrollList(List<PayrollStruct> payrollStructs){
        for (PayrollStruct payroll:payrollStructs){
            payrollStructRepository.save(payroll);
        }
        return payrollStructRepository.findAll();
    }

    public Department update(String id, Department updatedepartment) {
        updatedepartment.setId(id);
        return departmentRepository.save(updatedepartment);
    }
    public List<PayrollStruct> getPayrollStruct(){
        return payrollStructRepository.findAll();
    }
    public PayrollStruct getPayrollStruct(Department department) {
        return departmentRepository.findAll().stream()
                .filter(department1 -> department1.getDepartment().equalsIgnoreCase(department.getDepartment()))
                .findFirst().get().getPayrollStruct();
    }

    public void savePayroll(PayrollStruct payrollStruct) {
        payrollStructRepository.save(payrollStruct);
    }

    public Department findDepartment(String id) {
        return departmentRepository.findById(id).get();
    }
    public List<Department>  getDepartment() {
        System.out.println(departmentRepository.findAll());
        return departmentRepository.findAll().stream().toList();
    }

}
