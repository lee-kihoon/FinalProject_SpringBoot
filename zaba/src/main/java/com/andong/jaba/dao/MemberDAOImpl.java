package com.andong.jaba.dao;


import java.util.List;

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
	
	@Override
	public void trainerRegister(TrainerVO vo) throws Exception {
		sql.insert("memberMapper.trainerRegister", vo);
	}
	
	public List<TrainerVO> trainerSelect() throws Exception {
		return sql.selectList("memberMapper.trainerSelect");
	}
	
	// 로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return sql.selectOne("memberMapper.login", vo);
	}
	
	// 아이디 중복확인
	@Override
	public int idCheck(MemberVO vo) throws Exception{
		int result = sql.selectOne("memberMapper.idCheck", vo);
		return result;
	}

	// 트레이너 중복확인
	@Override
	public int trainerCheck(TrainerVO vo) throws Exception{
		int result = sql.selectOne("memberMapper.trainerCheck", vo);
		return result;
	}

	// 멤버 중복확인
	@Override
	public void memberUpdate(MemberVO vo) throws Exception{
		sql.update("memberMapper.memberUpdate", vo);
	}

	// 트레이너 중복확인
	@Override
	public void trainerUpdate(TrainerVO vo) throws Exception{
		sql.update("memberMapper.trainerUpdate", vo);
	}


	@Override
    public void CountExerciseReg(String UserId) throws Exception {
      sql.insert("memberMapper.CountExerciseReg", UserId);
    }

	//	트레이너 인증 대기중인 목록 Select
	@Override
	public List<MemberVO> trainerAcceptWait() throws Exception {
		return sql.selectList("memberMapper.trainerAcceptWait");
	}
	
	//	트레이너 인증 대기자 트레이너로 변경 Update
	@Override
	public void trainerAcceptComplete(String UserId) throws Exception {
		sql.update("memberMapper.trainerAcceptComplete", UserId);
	}
	
	//	 회원가입시, 트레이너 테이블 UserId Insert
	//	@Override
	//	public void trainerReg(String UserId) throws Exception {
	//		sql.insert("memberMapper.trainerReg", UserId);
	//	}
	
	// 파일정보 DB에 저장
	//	@Override
	//	public void trainerCertificate(TrainerVO vo) throws Exception {
	//		sql.update("memberMapper.trainerCertificate", vo);
	//	}
	
}