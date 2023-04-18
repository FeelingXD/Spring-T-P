package com.fastcampus.carinfo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car")
@Data
public class Car extends BaseEntity {
  public Car(){}
  public Car(String modelName, Company company, Integer passengerCapacity){
    this.modelName=modelName;
    this.company = company;
    this.passengerCapacity = passengerCapacity;
    super.setCreatedAt(new Date());
    super.setUpdatedAt(new Date());
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "model_name")
  private String modelName;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="company_id")
  private Company company;

  @Column(name = "passenger_capacity")
  private Integer passengerCapacity;

}
