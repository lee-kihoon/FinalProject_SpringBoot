package com.andong.jaba.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


import com.andong.jaba.dao.MemberDAO;
import com.andong.jaba.vo.MemberVO;
import com.andong.jaba.vo.TrainerVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	@Inject MemberDAO dao;

	@Override
	public void register(MemberVO vo) throws Exception {
		dao.register(vo);
		
	}
	
	@Override
	public void trainerRegister(TrainerVO vo) throws Exception {
		dao.trainerRegister(vo);
		
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
	@Override
	public List<TrainerVO> trainerSelect() throws Exception {
		return dao.trainerSelect();	
	}

	@Override
	public int idCheck(MemberVO vo) throws Exception{
		int result = dao.idCheck(vo);
		return result;
	}

	@Override
	public int trainerCheck(TrainerVO vo) throws Exception{
		int result = dao.trainerCheck(vo);
		return result;
	}
	
	@Override
	public void memberUpdate(MemberVO vo) throws Exception{
		dao.memberUpdate(vo);
	}

	@Override
	public void trainerUpdate(TrainerVO vo) throws Exception{
		dao.trainerUpdate(vo);
	}

	@Override
   	public void CountExerciseReg(String UserId) throws Exception {
      dao.CountExerciseReg(UserId);
   	}

	//	트레이너 인증 대기중인 목록 Select
	@Override
	public List<MemberVO> trainerAcceptWait() throws Exception {
		return dao.trainerAcceptWait();
	}

	//	트레이너 인증 대기자 트레이너로 변경 Update
	@Override
	public void trainerAcceptComplete(String UserId) throws Exception {
		dao.trainerAcceptComplete(UserId);
	}
	
	//	@Override
	//	public void trainerReg(String UserId) throws Exception {
	//		dao.trainerReg(UserId);
	//	}
	
	//	파일정보 DB에 저장
	//	@Override
	//	public void trainerCertificate(TrainerVO vo) throws Exception {
	//		dao.trainerCertificate(vo);
	//	}
}