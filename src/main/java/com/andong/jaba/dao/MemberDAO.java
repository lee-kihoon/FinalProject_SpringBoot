package com.andong.jaba.dao;

import com.andong.jaba.vo.MemberVO;
import com.andong.jaba.vo.TrainerVO;

public interface MemberDAO {
	// ȸ������
	public void register(MemberVO vo) throws Exception;
	// Ʈ���̳� ���� �Է�
	public void trainerRegister(TrainerVO vo) throws Exception;
	
	// �α���
	public MemberVO login(MemberVO vo) throws Exception;
	//Ʈ���̳� �ʴ��ڵ� ��ȸ
	public TrainerVO trainerSelect(TrainerVO vo) throws Exception;
}