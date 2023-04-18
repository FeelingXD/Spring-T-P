package com.fastcampus.carinfo.service;

import com.fastcampus.carinfo.domain.Car;
import com.fastcampus.carinfo.domain.Company;
import com.fastcampus.carinfo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CarService {
  @Autowired
  CarRepository carRepository;
  @Autowired
  CompanyService companyService;

  public Car saveCarInputDto(CarInputDTO carInputDTO) {
    Car car = new Car();
    car.setModelName(carInputDTO.getModelName());
    car.setPassengerCapacity(carInputDTO.getPassengerCapacity());
    car.setCompany(companyService.find(carInputDTO.getCompanyId()));
    car.setCreatedAt(new Date());
    car.setUpdatedAt(new Date());

    return carRepository.save(car);
  }

  public Page<Car> getCarPage(Pageable pageable) {
    int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
    pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));

    return carRepository.findAll(pageable);
  }

  public void saveAll(List<Car> carList) {
    carRepository.saveAll(carList);
  }

  public List<Car> getCarList() {
    return carRepository.getCarListByFetchJoin();
  }
}
