package com.employee.EmployeeDatabaseManagement.EDM.repository;

import com.employee.EmployeeDatabaseManagement.EDM.model.StringIDGenerator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDGeneratorRepository extends MongoRepository<StringIDGenerator,String> {
}
