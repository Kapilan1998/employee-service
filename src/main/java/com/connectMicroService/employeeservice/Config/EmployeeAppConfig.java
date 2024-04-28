package com.connectMicroService.employeeservice.Config;

import org.modelmapper.ModelMapper;
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



//  ModelMapper used for mapping one object model to another, automatically handling the conversion of fields with similar names and types
    // i just put dependency of this, but didn't used
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
