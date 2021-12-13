package com.andong.jaba.dao;

import java.util.List;

import com.andong.jaba.vo.MemberVO;
import com.andong.jaba.vo.TrainerVO;

public interface MemberDAO {
	// 회원가입
	public void register(MemberVO vo) throws Exception;

	// 트레이너 정보 입력
	public void trainerRegister(TrainerVO vo) throws Exception;
	
	// 로그인
	public MemberVO login(MemberVO vo) throws Exception;

	// 트레이너 초대코드 조회
	public List<TrainerVO> trainerSelect() throws Exception;

	// 아이디 중복확인
	public int idCheck(MemberVO vo) throws Exception;

	// 트레이너 아이디 중복확인
	public int trainerCheck(TrainerVO vo) throws Exception;

	// 회원 정보 수정
	public void memberUpdate(MemberVO vo) throws Exception;

	// 트레이너 정보 수정
	public void trainerUpdate(TrainerVO vo) throws Exception;

	// 운동 횟수 회원정보 insert
	public void CountExerciseReg(String UserId) throws Exception;

	//	트레이너 인증 대기중인 목록 Select
	public List<MemberVO> trainerAcceptWait() throws Exception;
	
	//	트레이너 인증 대기자 트레이너로 변경 Update
	public void trainerAcceptComplete(String UserId) throws Exception;
	
	//	 회원가입시, 트레이너 테이블 UserId Insert
	//	public void trainerReg(String UserId) throws Exception;
	
	// 파일정보 DB에 저장
	// 트레이너 파일 정보 insert
	//	public void trainerCertificate(TrainerVO vo) throws Exception;
}