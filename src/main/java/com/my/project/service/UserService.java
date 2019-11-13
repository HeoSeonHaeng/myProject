package com.my.project.service;

import java.util.List;

import com.my.project.contents.AuthInfo;
import com.my.project.contents.Member;

public interface UserService {

	public List<Member> getUserInfo(Member vo);
	public AuthInfo loginCheck(Member vo);
	public Integer signUp(Member vo);
	public String idCheck(String id);
}
