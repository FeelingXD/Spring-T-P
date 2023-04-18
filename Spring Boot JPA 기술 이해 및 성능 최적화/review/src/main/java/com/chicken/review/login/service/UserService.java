package com.chicken.review.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chicken.review.login.mapper.ReviewMapper;
import com.chicken.review.login.mapper.UserMapper;
import com.chicken.review.login.repository.ReviewJPARepository;
import com.chicken.review.login.repository.UserJPARepository;
import com.chicken.review.login.vo.ReviewJPAVO;
import com.chicken.review.login.vo.ReviewVO;
import com.chicken.review.login.vo.UserJPAVO;
import com.chicken.review.login.vo.UserVO;

@Service
public class UserService {
	
	
	@Autowired
	private UserMapper userMapper;
	

	private UserJPARepository userRepository;

	public int updateUserJoin(UserVO userVO) {
		
		return userMapper.updateUserJoin(userVO);
	}
	
	

	public List<UserJPAVO> getUserJPAList() {
		
		return userRepository.findAll();
	}
	
	
}
