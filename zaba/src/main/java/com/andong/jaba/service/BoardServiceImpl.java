package com.andong.jaba.service;

import java.util.List;

import javax.inject.Inject;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.andong.jaba.dao.BoardDAO;
import com.andong.jaba.vo.BoardVO;
import com.andong.jaba.vo.CountExerciseVO;
import com.andong.jaba.vo.countVO;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
    @Inject
	private BoardDAO dao;
	
	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		dao.write(boardVO);
	}

	// 게시물 목록 조회
	@Override
	public List<BoardVO> list() throws Exception {

		return dao.list();
	}

	// 일일 운동량 조회
	@Override
	public CountExerciseVO todayCount(String userName) throws Exception{
		return dao.todayCount(userName);
	}

	// 토탈 운동량 조회
	@Override
	public CountExerciseVO totalCount() throws Exception{
		return dao.totalCount();
	}

	// 토탈 회원수 조회
	@Override
	public countVO totalMember() throws Exception{
		return dao.totalMember();
	}

	// 토탈 트레이너수 조회
	@Override
	public countVO totalTrainer() throws Exception{
		return dao.totalTrainer();
	}

	// 스쿼트 TOP5
	@Override
    public List<CountExerciseVO> topSquat() throws Exception{
		return dao.topSquat();
	}

	// 나의 운동량 조회 
    public CountExerciseVO myCount(String userName) throws Exception{
		return dao.myCount(userName);
	}

	// 날짜별 운동량 조회 
    public CountExerciseVO myDateCount(CountExerciseVO CEVO) throws Exception{
		return dao.myDateCount(CEVO);
	}
}
