package com.my.project.contents;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
