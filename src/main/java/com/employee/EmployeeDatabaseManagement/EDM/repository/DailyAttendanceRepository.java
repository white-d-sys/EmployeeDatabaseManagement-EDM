package com.employee.EmployeeDatabaseManagement.EDM.repository;

import com.employee.EmployeeDatabaseManagement.EDM.model.DailyAttendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyAttendanceRepository extends MongoRepository<DailyAttendance,String> {
}
