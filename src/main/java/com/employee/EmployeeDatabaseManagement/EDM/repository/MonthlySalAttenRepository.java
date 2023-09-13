package com.employee.EmployeeDatabaseManagement.EDM.repository;

import com.employee.EmployeeDatabaseManagement.EDM.model.MonthlySalAtten;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlySalAttenRepository extends MongoRepository<MonthlySalAtten, String> {
}
