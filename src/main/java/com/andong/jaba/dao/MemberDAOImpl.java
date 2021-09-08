package com.andong.jaba.dao;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.andong.jaba.vo.MemberVO;
import com.andong.jaba.vo.TrainerVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	SqlSession sql;
	// 회원가입
	@Override
	public void register(MemberVO vo) throws Exception {
		sql.insert("memberMapper.register", vo);
	}
	
	public void trainerRegister(TrainerVO vo) throws Exception {
		sql.insert("memberMapper.trainerRegister", vo);
	}
	
	public TrainerVO trainerSelect(TrainerVO vo) throws Exception {
		return sql.selectOne("memberMapper.trainerSelect", vo);
	}
	
	// 로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return sql.selectOne("memberMapper.login", vo);
	}
	
}