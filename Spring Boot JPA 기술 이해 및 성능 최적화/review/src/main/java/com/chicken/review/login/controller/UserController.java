package com.chicken.review.login.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.chicken.review.login.service.AwsS3Service;
import com.chicken.review.login.service.ReviewService;
import com.chicken.review.login.service.UserService;
import com.chicken.review.login.vo.ReviewJPAVO;
import com.chicken.review.login.vo.ReviewVO;
import com.chicken.review.login.vo.UserVO;






@Controller
public class UserController {
	
	//private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@Autowired
    private ReviewService reviewService;
	@Autowired
    private UserService userService;
	@Autowired
    private AwsS3Service awsService;
	
	@PostMapping("/kakaoLogin")
	@ResponseBody
	public int kakaoLogin(UserVO user) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	HttpServletRequest request = attr.getRequest();
    	HttpServletResponse response = attr.getResponse();
    	
		System.out.println("TEST");
		System.out.println(user.getEmail());
		System.out.println(user.getId());
		System.out.println(user.getNickname());
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        
		UsernamePasswordAuthenticationToken userAuthenication = new UsernamePasswordAuthenticationToken(user.getId(), "pass", roles);
		
		userAuthenication.setDetails(user);
		userService.updateUserJoin(user);
		
		SecurityContextHolder.getContext().setAuthentication(userAuthenication);
		HttpSession session= request.getSession();
		session.setAttribute("user", user);
		return 1;
	}
	
	
	@PostMapping("/fileUpload")
	@ResponseBody
	public int fileUpload(@RequestParam("mediaFile") MultipartFile file,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam(value="seq",required=false) int seq,
			@RequestParam(value="type",required=false) String type,
			Model model) throws IllegalStateException, IOException {
		
		
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	HttpServletRequest request = attr.getRequest();
    	HttpServletResponse response = attr.getResponse();
    	
    	HttpSession session= request.getSession();
    	UserVO user = (UserVO)session.getAttribute("user");
    	System.out.println(seq);
    	System.out.println(title);
		System.out.println(content);
		System.out.println(type);
		System.out.println(user.getId());
		
		
		
		//awsService.s3FileUpload(file, reviewVO, type);
		if(type!=null&&type.equals("jpa")) {
			ReviewJPAVO reviewVO = new ReviewJPAVO();

			reviewVO.setSeq(seq);
			reviewVO.setTitle(title);
			reviewVO.setContent(content);
			reviewVO.setUserId(user.getId());
			reviewService.jpaUpdate(reviewVO);
		}else {
			ReviewVO reviewVO = new ReviewVO();

			reviewVO.setSeq(seq);
			reviewVO.setTitle(title);
			reviewVO.setContent(content);
			reviewVO.setUserId(user.getId());
			reviewService.myBatisUpdate(reviewVO);
		}
		
		
		
		return 1;	
	}
	
	
	
	
    @RequestMapping(value="/login")
    public String login(Model model, String error, String logout, @RequestParam(value="type",required=false) String type) {
    	
    	

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	
    	
    	//model.addAttribute("reviewList", reviewService.getReviewJPAList());
    	
    	if(authentication != null) {
    		System.out.println("LOGIN"+type);
    		
    		

        	
        	//reviewService.getReviewJPAList();
        	if(type==null||type.equals("myBatis")) {
        		System.out.println("getReviewList");
        		model.addAttribute("reviewList", reviewService.getReviewList());
        	}else {
        		System.out.println("getReviewJPAList");
        		model.addAttribute("reviewList", reviewService.getReviewJPAList());
        	}
        	
        	if(type!=null)
    			model.addAttribute("type", type);
        	
    		return "login/login";
	    	//if(!authentication.getPrincipal().equals("anonymousUser"))
	    	//	return "redirect:/";
    	}

		System.out.println("LOGIN"+type);

		if(type==null||type.equals("myBatis")) {
			System.out.println("getReviewList");
    		model.addAttribute("reviewList", reviewService.getReviewList());
    	}else {

    		System.out.println("getReviewJPAList");
    		model.addAttribute("reviewList", reviewService.getReviewJPAList());
    	}
    	
		if(type!=null)
			model.addAttribute("type", type);
    	

        return "login/login";
    }

    
   
}
