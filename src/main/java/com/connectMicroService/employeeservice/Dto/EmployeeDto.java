package com.connectMicroService.employeeservice.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EmployeeDto {
    private int id;
    private String name;
    private String email;
    private String bloodGroup;
    private AddressResponseDto addressResponseDto;
    15.00
}
