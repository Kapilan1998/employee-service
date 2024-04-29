package com.connectMicroService.employeeservice.Converter;

import com.connectMicroService.employeeservice.Dto.EmployeeDto;
import com.connectMicroService.employeeservice.Entity.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class EmployeeConverter {

    public EmployeeDto convertToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setBloodGroup(employee.getBloodGroup());

        return employeeDto;
    }
}
