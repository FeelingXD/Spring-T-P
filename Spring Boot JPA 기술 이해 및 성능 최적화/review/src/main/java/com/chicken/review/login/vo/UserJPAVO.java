package com.chicken.review.login.vo; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data 
@Entity
@Table(name="UserJPA")
public class UserJPAVO {

	@Id
	String id;
	
	@Column
	String email;

	@Column
	String nickname;
	
	
}
