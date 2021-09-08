package com.andong.jaba.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

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
	public TrainerVO trainerSelect(TrainerVO vo) throws Exception {
		return dao.trainerSelect(vo);	
	}
	
}