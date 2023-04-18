package com.fastcampus.jpalecture.jpapeeknapply.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="user_group")
public class UserGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @Column(name="group_type_num")
  @Enumerated(value = EnumType.ORDINAL)
  private GroupTypeEnum groupType;

  @OneToMany(mappedBy = "userGroup", fetch = FetchType.EAGER)
  private List<User> userList;

  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name="created_at")
  private Date createdAt;

  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name="updated_at")
  private Date updatedAt;

  public UserGroup(){}

  public UserGroup(String name, String discription, GroupTypeEnum groupType, Date now){
    this.name = name;
    this.description = discription;
    this.groupType = groupType;
    this.createdAt = now;
    this.updatedAt = now;
  }
}
