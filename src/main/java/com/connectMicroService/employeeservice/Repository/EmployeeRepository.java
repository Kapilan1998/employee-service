package com.connectMicroService.employeeservice.Repository;

import com.connectMicroService.employeeservice.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
