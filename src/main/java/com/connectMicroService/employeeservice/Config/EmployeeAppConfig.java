package com.connectMicroService.employeeservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EmployeeAppConfig {

    // need to create bean for rest template usage
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
