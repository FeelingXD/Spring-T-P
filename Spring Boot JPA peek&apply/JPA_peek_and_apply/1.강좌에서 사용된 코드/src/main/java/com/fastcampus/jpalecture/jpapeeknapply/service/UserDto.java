package com.fastcampus.jpalecture.jpapeeknapply.service;

import lombok.Data;

@Data
public class UserDto {
  public UserDto(){}
  public UserDto(Long id, String name){
    this.id = id;
    this.name = name;
  }

  private Long id;
  private String name;

  public String toString(){
    return " ID : " + this.id.toString() + ", name : " + this.name ;
  }
}
