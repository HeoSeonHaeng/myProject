package com.my.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.project.contents.AuthInfo;
import com.my.project.contents.Member;
import com.my.project.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	@Override
	public List<Member> getUserInfo(Member vo) {
		// TODO Auto-generated method stub
		return userDAO.getUserInfo();
	}

	@Override
	public AuthInfo loginCheck(Member vo) {
		// TODO Auto-generated method stub
		return userDAO.loginCheck(vo);
	}

	@Override
	public Integer signUp(Member vo) {
		// TODO Auto-generated method stub
		return userDAO.signUp(vo);
	}

	@Override
	public String idCheck(String id) {
		// TODO Auto-generated method stub
		return userDAO.idCheck(id)>0 ? "T" : "F";
	}
	
	
}
