package com.andong.jaba.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andong.jaba.service.MemberService;
import com.andong.jaba.session.Session;
import com.andong.jaba.vo.MemberVO;
import com.andong.jaba.vo.TrainerVO;


@Controller
public class WelcomeController {
	Session se;
	@RequestMapping("/")
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping("/index2")
	public String index2() {
		return "index2";
	}
	

	@RequestMapping("/sign_up")
	public String register() {
		return "register";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@RequestMapping("/trainerRegister")
	public String trainerRegister() {
		return "trainer_register";
	}
		
	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@Inject
	MemberService service;
	
	// 회원가입 get
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public void getRegister() throws Exception {
		logger.info("get register");
	}
	
	// 회원가입 post
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String postRegister(@Valid MemberVO vo, BindingResult result, Model model) throws Exception {
		
		if (result.hasErrors()) {
			System.out.println(result);
			
			return "redirect:/sign_up";
		}
		logger.info("post register");
		service.register(vo);
		return "redirect:/";

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		logger.info("post login");
		
		HttpSession session = req.getSession();
		MemberVO login = service.login(vo);
		
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}else {
			session.setAttribute("member", login);
			se = new Session();
			se.setUserId(login.getUserId());
		}
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/trainerRegist")
	public String trainerRegist(TrainerVO vo) throws Exception {
		// 트레이너 정보 insert
		logger.info("post register");
		vo.setUserId(se.getUserId());
		service.trainerRegister(vo);
		
		return "redirect:/index";

	}
	
	@RequestMapping(value = "/goRoom", method = RequestMethod.GET)
	public String goRoom(TrainerVO vo, HttpServletResponse httpServletResponse, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		//String url = "https://sweet-pug-40.loca.lt/?room="+code;
		logger.info("create webRTC room");
		HttpSession session = req.getSession();
		TrainerVO tvo = service.trainerSelect(vo);
		session.setAttribute("room", tvo);
//		se = new Session();
//		se.setKeyCode(tvo.getKeyCode());
//		String code = se.getKeyCode();
//		httpServletResponse.sendRedirect("https://curly-elephant-59.loca.lt/?room="+code);
		return "redirect:/index";
	}
}
