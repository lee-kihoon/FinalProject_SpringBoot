package com.andong.jaba.service;

import java.util.List;



import com.andong.jaba.vo.MemberVO;
import com.andong.jaba.vo.TrainerVO;

public interface MemberService {

	public void register(MemberVO vo) throws Exception;
	
	public MemberVO login(MemberVO vo) throws Exception;
	
	public void trainerRegister(TrainerVO vo) throws Exception;
	
	public List<TrainerVO> trainerSelect() throws Exception;
	
	public int idCheck(MemberVO vo) throws Exception;

	public int trainerCheck(TrainerVO vo) throws Exception;

	public void memberUpdate(MemberVO vo) throws Exception;

	public void trainerUpdate(TrainerVO vo) throws Exception;

	public void CountExerciseReg(String UserId) throws Exception;

	//	트레이너 인증 대기중인 목록 Select
	public List<MemberVO> trainerAcceptWait() throws Exception; 
	
	//	트레이너 인증 대기자 트레이너로 변경 Update
	public void trainerAcceptComplete(String UserId) throws Exception;

	//	 회원가입시, 트레이너 테이블 UserId Insert
	//	public void trainerReg(String UserId) throws Exception;
	
	//	파일정보 DB에 저장
	// public void trainerCertificate(TrainerVO vo) throws Exception;
}