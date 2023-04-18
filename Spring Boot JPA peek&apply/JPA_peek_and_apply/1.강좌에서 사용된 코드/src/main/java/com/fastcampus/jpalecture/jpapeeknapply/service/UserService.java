package com.fastcampus.jpalecture.jpapeeknapply.service;

import com.fastcampus.jpalecture.jpapeeknapply.entity.*;
import com.fastcampus.jpalecture.jpapeeknapply.repository.UserRepo;
import com.fastcampus.jpalecture.jpapeeknapply.repository.UserRepoSpringDataJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
  static final Logger log = LoggerFactory.getLogger(UserService.class);

  @Autowired
  UserRepo userRepo;

  @Autowired
  UserRepoSpringDataJPA userRepoSpringDataJPA;

  @Autowired
  UserGroupService userGroupService;

  @Transactional
  public User find(long id){
    return userRepo.find(id);
  }

  @Transactional
  public User addUser(User user){
    return userRepo.save(user);
  }

  @Transactional
  public void addUser(){
    Date now = new Date();
    User user= new User();
    user.setName("Alice");
    user.setUserType(UserTypeEnum.USER);
    user.setCreatedAt(now);
    user.setUpdatedAt(now);
    user.setMemo("this is transient");

//    user.setUserGroup(null);
//    user.setUserGroup(userGroupService.find(1));

    userRepo.save(user);
  }


  @Transactional
  public User addAdmin(User user){
    return userRepo.save(user);
  }

  @Transactional
  public void addAdmin(){
    Date now = new Date();
    User user= new User();
    user.setName("Bob");
    user.setUserType(UserTypeEnum.ADMIN);
    user.setCreatedAt(now);
    user.setUpdatedAt(now);

//    user.setUserGroup(null);
//    user.setUserGroup(userGroupService.find(1));

    userRepo.save(user);
  }

  @Transactional
  public void setGroupToUser() {
    User user = new User("Gregory", UserTypeEnum.USER, new Date());
    user.setUserGroup(userGroupService.find(1L));

    userRepo.save(user);
  }

  @Transactional
  public void removeGroupFromUser() {
    UserGroup userGroup1 = userGroupService.find(1L);
    UserGroup userGroup2 = userGroupService.find(2L);
    User user = userRepo.find(7L);
    user.setUserGroup(null);
    userRepo.save(user);
  }

  @Transactional
  public void findById(){
    PageRequest pageRequest = PageRequest.of(0,10,Sort.by("name"));
    Page<User> result = userRepoSpringDataJPA.findAllById(1L, pageRequest);

    List<User> userList = result.getContent();
    int totalPages = result.getTotalPages();
    boolean hasNextpage = result.hasNext();
  }

  @Transactional
  public void findByNameSpringDataJPA(){
    PageRequest pageRequest = PageRequest.of(0,10, Sort.by("name"));
    List<User> result = userRepoSpringDataJPA.findByName("Alice", pageRequest);
  }

}
