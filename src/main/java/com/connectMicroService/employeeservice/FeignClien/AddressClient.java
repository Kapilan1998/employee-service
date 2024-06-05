package com.connectMicroService.employeeservice.FeignClien;

import com.connectMicroService.employeeservice.Dto.AddressResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "abc", url="localhost:5000/api/v1")
//full url = localhost:5000/api/v1/get-address/3
public interface AddressClient {

    @GetMapping("/get-address/{empId}")
    AddressResponseDto getAddressByEmployeeId(@PathVariable("empId") int id);
}
