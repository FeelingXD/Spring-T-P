package com.fastcampus.jpalecture.jpapeeknapply.repository;

import com.fastcampus.jpalecture.jpapeeknapply.entity.User;
import com.fastcampus.jpalecture.jpapeeknapply.entity.UserTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepoSpringDataJPA extends JpaRepository<User, Long> {
  static final Logger log = LoggerFactory.getLogger(UserRepoSpringDataJPA.class);

  List<User> findByName(final String name);

  List<User> findByNameAndUserType(String name, UserTypeEnum userTypeEnum);



  @Query("select user from User user where user.userType = ?1")
  List<User> findByUserType(UserTypeEnum userTypeEnum);

  @Query("SELECT user FROM User user where user.createdAt bewteen ?1 to ?2")
  List<User> getUserByCreatedAt(Date from , Date to);

  @Query(value = "select user from Users user where user.userType = ?0 and user.name = ?1",
  nativeQuery = true)
  List<User> findByUserTypeAndName(UserTypeEnum userTypeEnum, String name);

  //count query 사용
  Page<User> findAllById(Long id , Pageable pageable);

  List<User> findByName(String name, Pageable pageable);


}