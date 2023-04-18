package com.fastcampus.jpalecture.jpapeeknapply.service;

import lombok.Data;

@Data
public class UserAndGroupDto {
  private String userName;
  private String groupName;

  public UserAndGroupDto(){}
  public UserAndGroupDto(String userName, String groupName){
    this.userName = userName;
    this.groupName = groupName;
  }

  public String toString(){
    return "UserName : " + this.userName + ", GroupName : " + groupName;
  }

}
