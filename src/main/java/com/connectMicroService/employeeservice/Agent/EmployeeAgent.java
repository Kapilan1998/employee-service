package com.connectMicroService.employeeservice.Agent;

import com.connectMicroService.employeeservice.Converter.EmployeeConverter;
import com.connectMicroService.employeeservice.Dto.EmployeeDto;
import com.connectMicroService.employeeservice.Entity.Employee;
import com.connectMicroService.employeeservice.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeAgent {
    private final EmployeeService employeeService;
    private final EmployeeConverter employeeConverter;

    public EmployeeDto getEmployeeDetails(int id) {
        Employee employee = employeeService.getEmployeeDetails(id);
        return employeeConverter.convertToDto(employee);
    }
}
