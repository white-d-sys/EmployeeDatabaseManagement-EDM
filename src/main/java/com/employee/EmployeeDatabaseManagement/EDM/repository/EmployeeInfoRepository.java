package com.employee.EmployeeDatabaseManagement.EDM.repository;

import com.employee.EmployeeDatabaseManagement.EDM.model.EmployeeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInfoRepository extends MongoRepository<EmployeeInfo,String> {
}
