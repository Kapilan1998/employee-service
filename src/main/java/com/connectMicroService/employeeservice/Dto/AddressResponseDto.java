package com.connectMicroService.employeeservice.Dto;

import lombok.Data;

@Data
public class AddressResponseDto {
    private int id;
   private String firstLane;
   private String lastLane;
   private long zip;
   private String state;
}
