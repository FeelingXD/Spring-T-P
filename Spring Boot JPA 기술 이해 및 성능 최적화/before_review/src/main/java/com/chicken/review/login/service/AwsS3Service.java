package com.chicken.review.login.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.chicken.review.login.mapper.ReviewMapper;
import com.chicken.review.login.vo.ReviewVO;

@Service
public class AwsS3Service {
	private static final String BUCKET_NAME = "reviewaws";
	private static final String ACCESS_KEY = "AKIAS7ZSK5WMPFKEIAUK";//AKIAS7ZSK5WMFVROAG4L
	private static final String SECRET_KEY = "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";//AlbUtiGyNiRsIpELEPKCQnhdEep4bNW0cnSM6ig7
	
	private AmazonS3 s3;
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	public AwsS3Service() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		
		s3 = AmazonS3Client.builder()
				.withRegion(Regions.AP_NORTHEAST_2) /* 서울 */
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
	}
	
	public void s3FileUpload(MultipartFile file, ReviewVO reviewVO) throws IllegalStateException, IOException {
		if(file != null && !file.getOriginalFilename().equals("")) {
			System.out.println(file.getOriginalFilename());
			
			File localFile = new File("/Users/jinhobae/Downloads/" + file.getOriginalFilename());
			file.transferTo(localFile);
		    
			System.out.println(localFile.getName());
			PutObjectRequest obj = new PutObjectRequest(BUCKET_NAME, localFile.getName(), localFile);
			obj.setCannedAcl(CannedAccessControlList.PublicRead);
			String imageUrl = "https://reviewaws.s3.ap-northeast-2.amazonaws.com/"+localFile.getName();
			s3.putObject(obj);
			System.out.println(imageUrl);
			reviewVO.setS3ImageUrl(imageUrl);
			
			reviewMapper.insertReview(reviewVO);
		}else {
			reviewMapper.insertReview(reviewVO);
		}
	}
}
