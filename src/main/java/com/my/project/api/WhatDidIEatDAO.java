package com.my.project.api;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.project.model.Account;

@Repository
public class WhatDidIEatDAO {
	protected static final String NAMESPACE = "com.my.project.api.";

	@Autowired
	private SqlSession sqlSession;

	public String selectName(){
		return sqlSession.selectOne(NAMESPACE + "selectName");
	}
	
	public List<Account> getAccountList() {
		return sqlSession.selectList(NAMESPACE + "getAccountList");
	}
	
	public Boolean insertAccount(Account excelInfo) {
		return sqlSession.insert(NAMESPACE + "insertAccount", excelInfo) > 0 ? true : false;
	}
}
