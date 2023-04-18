package com.fastcampus.jpalecture.jpapeeknapply.entity;

import javax.persistence.*;

@Entity
@Table(name="USERS_DETAIL")
public class UserDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="zipcode")
  private int zipcode;

  @OneToOne
  @JoinColumn(name="user_id")
  private User user;
}
