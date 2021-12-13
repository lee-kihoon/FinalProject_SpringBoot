package com.andong.jaba.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.andong.jaba.service.BoardService;
import com.andong.jaba.vo.BoardVO;
import com.andong.jaba.vo.MemberVO;
import com.andong.jaba.vo.CountExerciseVO;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	// 게시판 글 작성 화면
	@RequestMapping(value = "/writeView", method = RequestMethod.GET)
	public String writeView(HttpServletRequest req) throws Exception{
		logger.info("writeView");
        HttpSession session = req.getSession();
        MemberVO li = (MemberVO) session.getAttribute("member");

        if(li.getUserType()==0){
            System.out.println("잘못된 접근!");
            return "redirect:/index2";
        }else{
            return "trainer_select";
        }
	}
	
	// 게시판 글 작성 Insert 동작 부분
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO boardVO) throws Exception{

		logger.info("write");
		
		service.write(boardVO);
		
		return "trainer_select";
	}
	
    // 게시판 목록 조회
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest req) throws Exception{
		logger.info("list");
		HttpSession session = req.getSession();
        List<BoardVO> li = service.list();
        //System.out.println(li);
		model.addAttribute("list", li);
		
		return "trainer_list";
		
	}

	// 날짜 체크 및 Node JS 로 값 전송하기
	@CrossOrigin("*")
	@RequestMapping(value = "/trainerURL", method = RequestMethod.GET)
	public String trainerURL(Model model, @RequestParam String roomcode, @RequestParam String tdate, HttpServletRequest req) throws Exception{
		System.out.println(roomcode + tdate);
		HttpSession session = req.getSession();
        MemberVO li = (MemberVO) session.getAttribute("member");

		model.addAttribute("roomCode", roomcode);
		model.addAttribute("tdate", tdate);
		return "trainer_list_URL";
		
	}

    @RequestMapping(value = "/view")
	public String view() throws Exception{
		
		return "course2";
		
	}

	@RequestMapping(value = "/todayCount", method = RequestMethod.GET)
	public String dashBoard(CountExerciseVO ceVO, Model model, HttpServletRequest req, @RequestParam String date) throws Exception{
		System.out.println(date);
		
		
		HttpSession session = req.getSession();
        MemberVO li = (MemberVO) session.getAttribute("member");
		if(li == null ){ // 세션 정보가 없으면 메인페이지로 돌려주기
			return "index2";
		}else{
			//System.out.println(service.topSquat());
			CountExerciseVO selectDateCount = new CountExerciseVO();
			if(date.equals("")){
				return "redirect:/todayCount?date=2021-01-01";
			}
			selectDateCount.setDate(date);
			selectDateCount.setUserName(li.getUserId());
			System.out.println(selectDateCount);

			CountExerciseVO totalCount = service.totalCount();
		
			model.addAttribute("myCount", service.myCount(li.getUserId()));
			model.addAttribute("topSquat", service.topSquat());
			model.addAttribute("totalMember", service.totalMember());
			model.addAttribute("totalTrainer", service.totalTrainer());
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("dateCount", service.myDateCount(selectDateCount));
			//model.addAttribute("todayCount", service.todayCount(li.getUserId()));
			return "dashboard";
		}
		
	}



}

