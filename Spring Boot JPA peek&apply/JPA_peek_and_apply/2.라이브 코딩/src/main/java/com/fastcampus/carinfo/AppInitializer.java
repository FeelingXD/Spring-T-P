package com.fastcampus.carinfo;

import com.fastcampus.carinfo.domain.Car;
import com.fastcampus.carinfo.domain.Company;
import com.fastcampus.carinfo.service.CarService;
import com.fastcampus.carinfo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AppInitializer {
  @Autowired
  CompanyService companyService;
  @Autowired
  CarService carService;

  @PostConstruct
  private void init(){
    Company company = new Company();
    company.setUpdatedAt(new Date());
    company.setCreatedAt(new Date());
    company.setCompanyNation("이탈리아");
    company.setCompanyName("페라리");

    Company company1 = new Company();
    company1.setUpdatedAt(new Date());
    company1.setCreatedAt(new Date());
    company1.setCompanyNation("이탈리아");
    company1.setCompanyName("람보르기니");

    Company company2 = new Company();
    company2.setUpdatedAt(new Date());
    company2.setCreatedAt(new Date());
    company2.setCompanyNation("독일");
    company2.setCompanyName("BMW");

    Company company3 = new Company();
    company3.setUpdatedAt(new Date());
    company3.setCreatedAt(new Date());
    company3.setCompanyNation("한국");
    company3.setCompanyName("현대");

    companyService.save(company);
    companyService.save(company1);
    companyService.save(company2);
    companyService.save(company3);

    List<Company> companyList = new ArrayList<>();
    companyList.add(new Company("기아","한국"));
    companyList.add(new Company("볼보","스웨덴"));
    companyList.add(new Company("르노","프랑스"));
    companyList.add(new Company("테슬라","미국"));
    companyList.add(new Company("뷰익","미국"));
    companyList.add(new Company("캐딜락","미국"));
    companyList.add(new Company("쉐보레","미국"));
    companyList.add(new Company("GMC","미국"));
    companyList.add(new Company("허머","미국"));
    companyList.add(new Company("폰티악","미국"));
    companyList.add(new Company("포드","미국"));
    companyList.add(new Company("링컨","미국"));
    companyList.add(new Company("크라이슬러","미국"));
    companyList.add(new Company("지프","미국"));

    companyService.saveAll(companyList);

    List<Car> carList = new ArrayList<>();
    carList.add(new Car("M3",company2,5));
    carList.add(new Car("M1",company2,5));
    carList.add(new Car("M2",company2,5));
    carList.add(new Car("M4",company2,5));
    carList.add(new Car("M5",company2,5));
    carList.add(new Car("M6",company2,5));
    carList.add(new Car("소나타" , company3, 5));
    carList.add(new Car("아반떼" , company3, 5));
    carList.add(new Car("투스카니" , company3, 5));
    carList.add(new Car("아이오닉" , company3, 5));
    carList.add(new Car("그랜저" , company3, 5));
    carList.add(new Car("아슬란" , company3, 5));
    carList.add(new Car("투싼" , company3, 5));
    carList.add(new Car("모하비" , company3, 5));
    carService.saveAll(carList);
  }
}
