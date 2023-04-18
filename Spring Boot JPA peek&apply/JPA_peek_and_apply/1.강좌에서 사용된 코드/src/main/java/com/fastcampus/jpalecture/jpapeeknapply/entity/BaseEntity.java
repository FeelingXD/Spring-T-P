package com.fastcampus.jpalecture.jpapeeknapply.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity {

  private Date createdAt;
  private Date updatedAt;
}
