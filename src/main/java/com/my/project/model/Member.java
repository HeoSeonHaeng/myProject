package com.my.project.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Alias("member")
public class Member {

	String userNo;
	String userId;
	String password;
	String loginKeeping;
	String userNm;
	String userIdSignup;
	String userNameSignup;
	String emailSignup;
	String passwordSignup;
	
	int	isUser;
}
