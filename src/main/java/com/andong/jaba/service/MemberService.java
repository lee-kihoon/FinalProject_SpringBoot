package com.andong.jaba.service;

import java.util.Map;

import org.springframework.validation.Errors;

import com.andong.jaba.vo.MemberVO;
import com.andong.jaba.vo.TrainerVO;

public interface MemberService {

	public void register(MemberVO vo) throws Exception;
	
	public MemberVO login(MemberVO vo) throws Exception;
	
	public void trainerRegister(TrainerVO vo) throws Exception;
	
	public TrainerVO trainerSelect(TrainerVO vo) throws Exception;
	
}