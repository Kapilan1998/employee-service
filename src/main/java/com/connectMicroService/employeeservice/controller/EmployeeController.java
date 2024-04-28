package com.connectMicroService.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j

public class EmployeeController {

    @GetMapping("/employee-detail")
    public String displayEmployeeDetail(){
        return "Name : Edwin , email :edwinmose@gmail.com";
    }
}
