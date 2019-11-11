package com.my.project.contents;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class AuthInfo {
	
	String userId;
	String userName;
}
