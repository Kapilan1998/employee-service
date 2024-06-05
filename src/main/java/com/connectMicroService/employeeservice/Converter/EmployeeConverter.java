package com.connectMicroService.employeeservice.Converter;

import com.connectMicroService.employeeservice.Dto.AddressResponseDto;
import com.connectMicroService.employeeservice.Dto.EmployeeDto;
import com.connectMicroService.employeeservice.Entity.Employee;
import com.connectMicroService.employeeservice.FeignClien.AddressClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeConverter {
    private final RestTemplate restTemplate;
    @Value("${address.service.url}")
    private String addressServiceURL;

    private final AddressClient addressClient;
    public EmployeeDto convertToDto(Employee employee) {
        int id = employee.getId();
                // whenever using rest template, need to give full url
//        AddressResponseDto addressResponseDto = restTemplate.getForObject(addressServiceURL+"get-address/{id}", AddressResponseDto.class,id);
        AddressResponseDto addressResponseDto = addressClient.getAddressByEmployeeId(id);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setBloodGroup(employee.getBloodGroup());
//        employeeDto.setAddressResponseDto(addressResponseDto);
        employeeDto.setAddressResponseDto(addressResponseDto);
        return employeeDto;
    }
}
