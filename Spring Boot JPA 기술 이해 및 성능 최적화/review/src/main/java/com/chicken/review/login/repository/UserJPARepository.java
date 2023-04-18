package com.chicken.review.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chicken.review.login.vo.UserJPAVO;
 

public interface UserJPARepository  extends JpaRepository<UserJPAVO, String>{

}
