package com.chicken.review.login.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chicken.review.login.mapper.ReviewMapper;
import com.chicken.review.login.repository.ReviewJPARepository;
import com.chicken.review.login.vo.ReviewJPAVO;
import com.chicken.review.login.vo.ReviewVO;

@Service 
@Transactional
public class ReviewService {
	
	
	@Autowired
	private ReviewMapper reviewMapper;

	@Autowired
	private ReviewJPARepository reviewRepository;
	
	@PersistenceUnit
    EntityManagerFactory emf;
	

	public List<ReviewVO> getReviewList() {
		
		return reviewMapper.getReviewList();
	}
	
	

	public List<ReviewJPAVO> getReviewJPAList() {
		
		
		
		return reviewRepository.findAll();
	}
	
	

	public void jpaUpdate(ReviewJPAVO reviewVO) throws IllegalStateException, IOException {
		
	
		//JPA 입력 및 수정 장면
		if(reviewVO.getSeq()==0) {
			System.out.println("jpaUpdate Insert");
			ReviewJPAVO newReviewVO = new ReviewJPAVO();
			newReviewVO.setTitle(reviewVO.getTitle());
			newReviewVO.setContent(reviewVO.getContent());
			newReviewVO.setUserId(reviewVO.getUserId());
			newReviewVO.setCount(0);

			reviewRepository.save(newReviewVO);
		}else {
			reviewRepository.save(reviewVO);
		}

			
		
		
		
	}
	
	
	public void myBatisUpdate(ReviewVO reviewVO) throws IllegalStateException, IOException {
		
		
		//myBatis 입력 및 수정 장면
		if(reviewVO.getSeq()==0) {
			reviewMapper.insertReview(reviewVO);
		}else {
			reviewMapper.updateReview(reviewVO);
		}
	
			
	}

}
