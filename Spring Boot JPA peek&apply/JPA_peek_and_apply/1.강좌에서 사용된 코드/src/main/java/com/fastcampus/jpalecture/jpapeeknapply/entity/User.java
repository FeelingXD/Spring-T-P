package com.fastcampus.jpalecture.jpapeeknapply.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Users")
public class User extends BaseEntity{
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "user_type_str")
  @Enumerated(value = EnumType.STRING)
  private UserTypeEnum userType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_group_id")
  private UserGroup userGroup;

  @OneToOne(mappedBy = "user")
  private UserDetail userdetail;

//  @Temporal(value = TemporalType.TIMESTAMP)
//  @Column(name = "created_at")
//  private Date createdAt;
//
//  @Temporal(value = TemporalType.TIMESTAMP)
//  @Column(name = "updated_at")
//  private Date updatedAt;

  @Transient
  private String memo;


  public User() {
  }

  public User(String name, UserTypeEnum userType, Date now) {
    this.name = name;
    this.userType = userType;
//    this.createdAt = now;
//    this.updatedAt = now;
    super.setCreatedAt(now);
    super.setUpdatedAt(now);
  }
}
