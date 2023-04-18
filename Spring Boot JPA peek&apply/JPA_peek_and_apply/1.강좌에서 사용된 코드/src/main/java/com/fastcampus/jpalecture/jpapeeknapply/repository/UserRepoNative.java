package com.fastcampus.jpalecture.jpapeeknapply.repository;

import com.fastcampus.jpalecture.jpapeeknapply.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepoNative {
  static final Logger log = LoggerFactory.getLogger(UserRepoNative.class);

  @PersistenceContext
  EntityManager em;

  public List<User> findUserList() {
    String sql =
            "SELECT ID, NAME, USER_TYPE_STR, USER_GROUP_ID, USER_DETAIL, CREATED_AT, UPDATED_AT" +
                    "FROM USERS" +
                    "WHERE NAME = ?";
    Query nativeQuery = em.createNativeQuery(sql, User.class);
    nativeQuery.setParameter(1, "Alice");

    List<User> resultList = nativeQuery.getResultList();

    return resultList;
  }

}