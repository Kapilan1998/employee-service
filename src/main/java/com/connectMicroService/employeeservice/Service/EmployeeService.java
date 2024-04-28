package com.connectMicroService.employeeservice.Service;

import com.connectMicroService.employeeservice.Entity.Employee;
import com.connectMicroService.employeeservice.Exception.ServiceException;
import com.connectMicroService.employeeservice.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee getEmployeeDetails(int id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ServiceException("employee not found", "Bad request", HttpStatus.BAD_REQUEST));
    }
}
