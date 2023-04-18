package com.chicken.review.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.chicken.review.login.vo.ReviewJPAVO; 
 
public interface ReviewJPARepository extends JpaRepository<ReviewJPAVO, String>{

	List<ReviewJPAVO> findByTitle (String title);
	 
}
