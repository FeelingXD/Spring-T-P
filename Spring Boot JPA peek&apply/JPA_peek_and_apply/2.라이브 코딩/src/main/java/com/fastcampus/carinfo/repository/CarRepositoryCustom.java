package com.fastcampus.carinfo.repository;


import com.fastcampus.carinfo.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepositoryCustom {
  List<Car> getCarListByFetchJoin();
}
