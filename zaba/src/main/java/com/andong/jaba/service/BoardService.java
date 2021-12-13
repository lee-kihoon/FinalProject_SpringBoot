package com.andong.jaba.service;
import java.util.List;

import com.andong.jaba.vo.BoardVO;
import com.andong.jaba.vo.CountExerciseVO;
import com.andong.jaba.vo.countVO;
public interface BoardService {
    // 게시글 작성
	public void write(BoardVO boardVO) throws Exception;
    
    // 게시물 목록 조회
	public List<BoardVO> list() throws Exception;

    // 일일 운동량 조회
    public CountExerciseVO todayCount(String userName) throws Exception;

    // 토탈 운동량 조회
    public CountExerciseVO totalCount() throws Exception;

    // 토탈 회원수 조회
    public countVO totalMember() throws Exception;

    // 토탈 트레이너수 조회
    public countVO totalTrainer() throws Exception;

    // 스쿼트 TOP5
    public List<CountExerciseVO> topSquat() throws Exception;

    // 나의 운동량 조회 
    public CountExerciseVO myCount(String userName) throws Exception;

    // 날짜별 운동량 조회 
    public CountExerciseVO myDateCount(CountExerciseVO CEVO) throws Exception;
}
