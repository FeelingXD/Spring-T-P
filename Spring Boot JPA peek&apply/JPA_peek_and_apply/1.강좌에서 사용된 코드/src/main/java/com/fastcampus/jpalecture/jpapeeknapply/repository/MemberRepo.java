package com.fastcampus.jpalecture.jpapeeknapply.repository;

import com.fastcampus.jpalecture.jpapeeknapply.entity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class MemberRepo {
  static final Logger log = LoggerFactory.getLogger(MemberRepo.class);

  @Autowired
  EntityManagerFactory emf;

  @PersistenceContext
  EntityManager empc;

  public Member findMember(Long id) {
    EntityManager em = emf.createEntityManager();
    Member member = em.find(Member.class, id);

    em.close();
    log.info("member : " + member.getMemberId() + " / " +
            member.getName() + "/ " +
            member.getId());

    return member;

  }

  @Transactional
  public void save(Member member) {
    log.info("before member id: " + member.getId());
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();

      if(member.getId() != null){
        em.merge(member);
      } else{
        em.persist(member);
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
    log.info("after member id: " + member.getId());


  }

  public void remove(Long id) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();

      em.remove(em.find(Member.class, id));

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
  }

}