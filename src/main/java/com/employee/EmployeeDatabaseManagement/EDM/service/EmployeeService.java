package com.employee.EmployeeDatabaseManagement.EDM.service;

import com.employee.EmployeeDatabaseManagement.EDM.exception.EmployeeInfoNoSuchException;
import com.employee.EmployeeDatabaseManagement.EDM.model.Employee;
import com.employee.EmployeeDatabaseManagement.EDM.model.StringIDGenerator;
import com.employee.EmployeeDatabaseManagement.EDM.repository.EmployeeRepository;
import com.employee.EmployeeDatabaseManagement.EDM.repository.IDGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private IDGeneratorRepository idGeneratorRepository;
    @Autowired
    DepartmentService departmentService;

    @Autowired
    private EmailService emailService;
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Employee> listAll() {

        return employeeRepository.findAll();
    }
    public String getId(String department){
        if (idGeneratorRepository.findAll().isEmpty()){
            idGeneratorRepository.save(new StringIDGenerator(10001,10001));
            return "PD-DEPT-1001";
        }
        String id="PD"+department.substring(0,2).toUpperCase()+idGeneratorRepository.findAll().get(0).getEmployee();
        return id;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);

    }

    public Employee get(String id) {
        return employeeRepository.findById(id).orElseThrow(EmployeeInfoNoSuchException::new);
    }
    public Employee update(Employee employee){
        StringIDGenerator id =idGeneratorRepository.findAll().stream().findFirst().get();
        id.setEmployee(id.getEmployee()+1);
        idGeneratorRepository.save(id);
        return employeeRepository.save(employee);
    }

    public Employee update(String id, Employee updateEmployee) {
        Employee existingEmployee = get(id);
        existingEmployee.setId(updateEmployee.getId());
        existingEmployee.setName(updateEmployee.getName());
        existingEmployee.setEmail(updateEmployee.getEmail());
        existingEmployee.setNumber(updateEmployee.getNumber());
        existingEmployee.setDOJ(updateEmployee.getDOJ());
        existingEmployee.setDOL(updateEmployee.getDOL());
        existingEmployee.setAadharNo(updateEmployee.getAadharNo());
        existingEmployee.setPanNo(updateEmployee.getPanNo());

        return employeeRepository.save(existingEmployee);
    }

    public void delete(Long id) {

        employeeRepository.deleteById(String.valueOf(id));
    }
    public void addEmployee(Employee employee) {
        // Save the new employee to the database
        Employee savedEmployee = employeeRepository.save(employee);

        // Send confirmation email to the employee
        emailService.sendConfirmationEmail(savedEmployee.getEmail(), savedEmployee.getName());

        // Send confirmation email to the manager
//        Employee manager = savedEmployee.getManager();
        // Send confirmation email to team members
    }
    public List<Employee> getLast(){
        List<Employee> employees=new ArrayList<>();
        int i= (int) employeeRepository.findAll().stream().count()-1;
        int j=i;
        for (;j>=i;i--){
            employees.add(employeeRepository.findAll().stream().toList().get(i));
            if (j-2==i)break;
        }
        return employees;
    }
}


