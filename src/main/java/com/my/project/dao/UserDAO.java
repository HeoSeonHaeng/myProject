package com.my.project.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.project.contents.AuthInfo;
import com.my.project.contents.Member;



@Repository("userDAO")
public class UserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public List<Member> getUserInfo() {
		return sqlSession.selectList("userSql.getUserInfo");
	}

	public AuthInfo loginCheck(Member vo) {
		return sqlSession.selectOne("userSql.loginCheck", vo);
	}

	public Integer signUp(Member vo) {
		return sqlSession.insert("userSql.signUp", vo);
	}
	
	public Integer idCheck(String id) {
		return sqlSession.selectOne("userSql.idCheck", id);
	}
}
