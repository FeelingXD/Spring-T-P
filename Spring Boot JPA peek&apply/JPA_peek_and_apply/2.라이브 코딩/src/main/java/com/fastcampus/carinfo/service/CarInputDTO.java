package com.fastcampus.carinfo.service;

import lombok.Data;

@Data
public class CarInputDTO {
  private String modelName;
  private Long companyId;
  private Integer passengerCapacity;

}
