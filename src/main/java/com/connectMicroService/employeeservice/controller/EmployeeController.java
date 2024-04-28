package com.connectMicroService.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    @Autowired
    private RestTemplate restTemplate;  // rest client to make rest call (it may be deprecated in latest spring version)

    @GetMapping("/employee-detail")
    public String displayEmployeeDetail(){
                                // from which end point it is going to get data, it's return type
        String address = restTemplate.getForObject("http://localhost:5000/api/v1/view-address", String.class);
        return "Name : Edwin , email :edwinmose@gmail.com  " + address;
    }
}
