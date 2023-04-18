package com.fastcampus.carinfo.repository;


import com.fastcampus.carinfo.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CarRepositoryCustomImpl implements CarRepositoryCustom {
  @PersistenceContext
  EntityManager em;

  @Override
  public List<Car> getCarListByFetchJoin(){
    String query =" SELECT car FROM Car car join fetch car.company ";
    List<Car> carList = em.createQuery(query,Car.class).getResultList();

    return carList;
  }


}
