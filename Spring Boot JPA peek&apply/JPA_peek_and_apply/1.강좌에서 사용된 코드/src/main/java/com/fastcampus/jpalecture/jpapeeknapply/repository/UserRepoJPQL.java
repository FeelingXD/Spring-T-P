package com.fastcampus.jpalecture.jpapeeknapply.repository;

import com.fastcampus.jpalecture.jpapeeknapply.entity.User;
import com.fastcampus.jpalecture.jpapeeknapply.entity.UserGroup;
import com.fastcampus.jpalecture.jpapeeknapply.service.UserAndGroupDto;
import com.fastcampus.jpalecture.jpapeeknapply.service.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class UserRepoJPQL {
  static final Logger log = LoggerFactory.getLogger(UserRepoJPQL.class);

  @PersistenceContext
  EntityManager em;

  public List<User> findUserList() {
    String jpql = "select user from User as user where user.name = 'alice'";
    List<User> userList= em.createQuery(jpql, User.class).getResultList();

    return userList;
  }

  public void printAllUserNameTyped(){
    TypedQuery<User> query = em.createQuery("SELECT user From USER user", User.class);

    List<User> userList = query.getResultList();
    for(User user : userList){
      log.info("User Name: " + user.getName());
    }
  }

  public void printAllUserName(){
    Query query = em.createQuery("SELECT user.name, user.id, from User user");
    List resultList = query.getResultList();

    for(Object o : resultList){
      Object[] result = (Object[]) o;
      log.info("UserName: " + result[0] + ", user id: " + result[1]);
    }
  }

  public void printUserNameWithNamedParam(){
    String param = "Alice";
    TypedQuery<User> query = em.createQuery("SELECT user from User as user where user.name = :param",User.class);

    query.setParameter("param", param);
    List<User> userList = query.getResultList();

//    List<User> userList = em.createQuery(
//            "SELECT user from User as user where user.name = :param", User.class)
//            .setParameter("param", param)
//            .getResultList();

    for(User user : userList){
      log.info("User name : " + user.getName());
    }


  }


  public void printUserNameWithPositionalParam() {
    String param = "Alice";
    TypedQuery<User> query =
            em.createQuery("SELECT user from User as user where user.name = ?1",User.class);

    query.setParameter(1, param);
    List<User> userList = query.getResultList();
    for(User user : userList){
      log.info("User name : " + user.getName());
    }
  }

  public void printUserDto(){
    String jpql =
            "select new com.fastcampus.jpalecture.jpapeeknapply.service.UserDto(user.id, user.name) " +
                    "from User as user " +
                    "where user.name = 'alice'";

    List<UserDto> resultList = em.createQuery(jpql, UserDto.class).getResultList();
    for(UserDto userDto : resultList){
      log.info(userDto.toString());
    }
  }

  public void paging(){
    TypedQuery<User> query =
            em.createQuery("SELECT user from User as user Order by user.id asc", User.class);
    query.setFirstResult(0);
    query.setMaxResults(2);
    List<User> userList = query.getResultList();

    log.info("total count: " + userList.size());

    for(User user : userList){
      log.info("user name : " + user.getName() + ", user id : " + user.getId() );
    }

    query.setFirstResult(2);
    query.setMaxResults(3);
    log.info("total count: " + userList.size());
    for(User user : userList){
      log.info("user name : " + user.getName() + ", user id : " + user.getId() );
    }

  }

  public void innerJoin(){
    String query = " SELECT " +
            " NEW com.fastcampus.jpalecture.jpapeeknapply.service.UserAndGroupDto(user.name, group.name )" +
            "FROM User user INNER JOIN user.userGroup group ";

    List<UserAndGroupDto> userAndGroupDtoList = em.createQuery(query, UserAndGroupDto.class).getResultList();

    for(UserAndGroupDto ugdto : userAndGroupDtoList){
      log.info(ugdto.toString());
    }
  }


  public void outterJoin(){
    String query = " SELECT " +
            " NEW com.fastcampus.jpalecture.jpapeeknapply.service.UserAndGroupDto(user.name, group.name )" +
            "FROM User user LEFT OUTER JOIN user.userGroup group ";

    List<UserAndGroupDto> userAndGroupDtoList = em.createQuery(query, UserAndGroupDto.class).getResultList();

    for(UserAndGroupDto ugdto : userAndGroupDtoList){
      log.info(ugdto.toString());
    }
  }

  public void entityFetchJoin(){
    String query = " SELECT user from User user join fetch user.userGroup ";
    List<User> userList = em.createQuery(query, User.class).getResultList();

    for(User user : userList){
      log.info(" user name : " + user.getName() + ", group : " + user.getUserGroup().getName());
    }
  }

  public void collectionFetchJoin(){
    String query = "SELECT group from UserGroup group JOIN fetch group.userList ";
    List<UserGroup> userGroupList = em.createQuery(query, UserGroup.class).getResultList();

    for(UserGroup userGroup : userGroupList){
//      log.info(" group name: " + userGroup.getName() + ", user count + " + userGroup.getUserList().size() );
    }

  }

}