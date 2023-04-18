package com.fastcampus.carinfo.domain;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity {
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date createdAt;

  @Temporal(value = TemporalType.TIMESTAMP)
  private Date updatedAt;
}
