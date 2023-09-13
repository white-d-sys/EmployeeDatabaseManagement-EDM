package com.employee.EmployeeDatabaseManagement.EDM.repository;

import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeCred;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCredRepository extends MongoRepository<EmployeeCred, String> {
}
