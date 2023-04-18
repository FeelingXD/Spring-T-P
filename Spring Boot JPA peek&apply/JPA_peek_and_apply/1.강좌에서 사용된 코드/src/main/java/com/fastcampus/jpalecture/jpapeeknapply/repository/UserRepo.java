package com.fastcampus.jpalecture.jpapeeknapply.repository;

import com.fastcampus.jpalecture.jpapeeknapply.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class UserRepo {
  static final Logger log = LoggerFactory.getLogger(UserRepo.class);

  @PersistenceContext
  EntityManager em;

  public User find(Long id) {
    User user = em.find(User.class, id);

    return user;

  }

  @Transactional
  public User save(User user) {

    if(user.getId() != null){
      em.merge(user);
    } else {
      em.persist(user);
    }

    return user;
  }
}