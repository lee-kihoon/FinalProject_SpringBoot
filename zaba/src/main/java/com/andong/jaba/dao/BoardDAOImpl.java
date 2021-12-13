package com.andong.jaba.dao;
import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.andong.jaba.vo.BoardVO;
import com.andong.jaba.vo.CountExerciseVO;
import com.andong.jaba.vo.countVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
    @Inject
	private SqlSession sqlSession;
	
	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sqlSession.insert("memberMapper.boardInsert", boardVO);
	}

    // 게시물 목록 조회
	@Override
	public List<BoardVO> list() throws Exception {
	
		return sqlSession.selectList("memberMapper.boardList");

	}

	// 일일 운동량 조회
	@Override
	public CountExerciseVO todayCount(String userName) throws Exception{
		return sqlSession.selectOne("memberMapper.todayCount", userName);
	}

	// 토탈 운동량 조회
	@Override
	public CountExerciseVO totalCount() throws Exception{
		return sqlSession.selectOne("memberMapper.totalCount");
	}

	// 토탈 회원수 조회
	@Override
	public countVO totalMember() throws Exception{
		return sqlSession.selectOne("memberMapper.totalMember");
	}

	// 토탈 트레이너수 조회
	@Override
	public countVO totalTrainer() throws Exception{
		return sqlSession.selectOne("memberMapper.totalTrainer");
	}


    // 스쿼트 TOP5
	@Override
    public List<CountExerciseVO> topSquat() throws Exception{
		return sqlSession.selectList("memberMapper.topSquat");
	}

	// 나의 운동량 조회 
	@Override
    public CountExerciseVO myCount(String userName) throws Exception{
		return sqlSession.selectOne("memberMapper.myCount", userName);
	}

	
    // 날짜별 운동량 조회 
	@Override
    public CountExerciseVO myDateCount(CountExerciseVO CEVO) throws Exception{
		return sqlSession.selectOne("memberMapper.dateCount", CEVO);
	}
}
