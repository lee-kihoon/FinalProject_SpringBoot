package com.andong.jaba.dao;

import com.andong.jaba.vo.MemberVO;
import com.andong.jaba.vo.TrainerVO;

public interface MemberDAO {
	// 회원가입
	public void register(MemberVO vo) throws Exception;
	// 트레이너 정보 입력
	public void trainerRegister(TrainerVO vo) throws Exception;
	
	// 로그인
	public MemberVO login(MemberVO vo) throws Exception;
	//트레이너 초대코드 조회
	public TrainerVO trainerSelect(TrainerVO vo) throws Exception;
}