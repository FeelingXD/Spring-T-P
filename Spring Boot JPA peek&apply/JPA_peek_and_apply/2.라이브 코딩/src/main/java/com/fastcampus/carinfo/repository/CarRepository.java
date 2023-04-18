package com.fastcampus.carinfo.repository;


import com.fastcampus.carinfo.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>, CarRepositoryCustom {
}
