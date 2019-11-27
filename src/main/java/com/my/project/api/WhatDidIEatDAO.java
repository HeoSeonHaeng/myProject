package com.my.project.api;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.project.model.Account;
import com.my.project.model.WhatDidIEatVo;

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
	
	public Boolean insertAccount(Map<String, Object> map) {
		return sqlSession.insert(NAMESPACE + "insertAccount", map) > 0 ? true : false;
	}
	
	public List<WhatDidIEatVo> getTargetAccountList() {
		return sqlSession.selectList(NAMESPACE + "getTargetAccountList");
	}
}
