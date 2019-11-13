package com.my.project.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("whatEatDAO")
public class WhatDidIEatDAO {
	@Autowired
	private SqlSession sqlSession;
	
	
}
