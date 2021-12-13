package com.andong.jaba.controller;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.andong.jaba.mail.MailService;
import com.andong.jaba.mail.MailTo;
import com.andong.jaba.service.BoardService;
import com.andong.jaba.service.MemberService;
import com.andong.jaba.vo.CountExerciseVO;
import com.andong.jaba.vo.MemberVO;
import com.andong.jaba.vo.TrainerVO;



@Controller
public class WelcomeController {

	@Inject
	MemberService service;

	@Inject
	BoardService board_service;
	
	@RequestMapping("/")
	public String welcome(MemberVO vo, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		MemberVO isAlready = (MemberVO) session.getAttribute("member");
		if(isAlready==null){
			return "welcome";
		}
		else{
			return "redirect:/index2";
		}
		
	}
	
	// 메인화면 입장
	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public String index2(Model model) throws Exception{
		logger.info("list");
		List<TrainerVO> trainerList = service.trainerSelect();
		
		CountExerciseVO totalCount = board_service.totalCount(); // 운동 총량 구하기

		model.addAttribute("totalCount", totalCount);

		model.addAttribute("trainerList", trainerList);
		return "index2";
	}

	@RequestMapping("/sign_up")
	public String register() {
		
		return "register";
	}

	@RequestMapping("/trainerRegister")
	public String trainerRegister() {
		return "trainer_register";
	}
		
	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	// 회원가입 get 처음 들어오면 일로 들어와짐
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String getRegister(Model model) throws Exception {
		logger.info("get register");
		model.addAttribute("vo", new MemberVO());
		return "register";
	}

	// 회원가입 post 요청
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String postRegister(@ModelAttribute("vo") @Valid MemberVO vo, BindingResult result, Model model) throws Exception {
		
			//		MemberVO 에 @Valid 정규식에 어긋날 시 if문 수행 
					if (result.hasErrors()) {
						System.out.println(result);
						model.addAttribute("vo", vo);
						return "register";
					} // end if
					
					// 아이디 체크
					int resultIdCheck = service.idCheck(vo);
			
					try {
						if (resultIdCheck == 1) { // 아이디 중복시
							return "register";
						} else if (resultIdCheck == 0) { // 아이디 미중복시
							logger.info("post register");
							service.register(vo);
							service.CountExerciseReg(vo.getUserId());
			
						}
					} catch (Exception e) {
						throw new RuntimeException();
					}
					
			//		트레이너로 회원가입 진행시
					if (vo.getUserType() == 2) {
						// service.trainerReg(vo.getUserId());
						String path = "/file?UserId=" + vo.getUserId();
						return "redirect:" + path;
					} // end if
			
					return "redirect:/";

	}

	// idCheck
	@ResponseBody
	@RequestMapping(value="/idCheck", method = RequestMethod.POST)
	public int idCheck(MemberVO vo) throws Exception{
		int result = service.idCheck(vo);
		return result;
	}

	// 트레이너 아이디 체크
	@ResponseBody
	@RequestMapping(value="/trainerCheck", method = RequestMethod.POST)
	public int trainerCheck(TrainerVO vo) throws Exception{
		int result = service.trainerCheck(vo);
		return result;
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {

		

		logger.info("post login");

		// session 생성
		HttpSession session = req.getSession();
		MemberVO login = service.login(vo);
		
		
		// login 객체가 null 일 경우, if문 수행
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:/";
		}else {
			session.setAttribute("member", login);

			//관리자 계정으로 로그인 할 경우, if문 수행
			if (login.getUserType() == 9) {
				return "redirect:/admin_accept";
			} //end if

		}
		return "redirect:/index2";
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/index2";
	}
	
	// 트레이너 정보 입력
	@RequestMapping(value = "/trainerRegist", method = RequestMethod.POST)
	public String trainerRegist(@Valid TrainerVO vo, BindingResult result, Model model) throws Exception {
		// 트레이너 정보 insert

		int resultTrainerCheck = service.trainerCheck(vo);
		try{
			if(resultTrainerCheck == 1){		// 트레이너 중복시
				return "memberUpdateView";
			}else if(resultTrainerCheck == 0){	// 트레이너 미중복시
				logger.info("post register");
				service.trainerRegister(vo);
			}
		}catch (Exception e){
			throw new RuntimeException();
		}
		
		return "redirect:/index2";

	}
	
	// 회원 정보, 트레이너 정보 수정 get 요청 및 view
	@RequestMapping(value="/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		return "memberUpdateView";
	}
	
	// 회원정보 수정 post 요청
	@RequestMapping(value="/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
	
		service.memberUpdate(vo);
	
		session.invalidate();
	
		return "redirect:/";
	}
	
	// 트레이너정보 수정 post 요청
	@RequestMapping(value="/trainerUpdate", method = RequestMethod.POST)
	public String trainerUpdate(TrainerVO vo, HttpSession session) throws Exception{

		service.trainerUpdate(vo);
	
		return "redirect:/index2";
	}

	// mail
	//	Contact Me 메일 전송.
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String mailContact(HttpServletRequest req, HttpServletResponse res) throws MessagingException, IOException {
	//	form 태그 파라미터 값 가져오기
		String Name = req.getParameter("name");
		String Email = req.getParameter("email");
		String Message = req.getParameter("message");

//		메일 전송
		try {
			MailTo mailTo = new MailTo();
//			메일 설정 부분
			mailTo.setAddress("leekh.developer@gmail.com");
			mailTo.setFromAddress(Email);
			mailTo.setTitle(Name);
			mailTo.setMessage(Message);
			
//			메일 전송 수행
			mailService.sendMail(mailTo);

//			자바스크립트 alert 수행
			res.setContentType("text/html; charset=euc-kr");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('메일이 성공적으로 전송되었습니다!'); </script>");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/index2";
	}

	//	트레이너로 가입시, 추가 첨부파일 메일 보내기.
	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(path = "/mail", method = RequestMethod.GET)
	public String mail(Model model, HttpServletRequest req, HttpServletResponse res)
			throws MessagingException, IOException {
//		Mail mail = new Mail();
//		mail.setMailFrom("leekhdeveloper@gmail.com");
//		mail.setMailTo("leekhdeveloper@gmail.com");
//        mail.setMailSubject("Spring Boot - Email Example");
//        mail.setMailContent("Learn How to send Email using Spring Boot!!!\n\nThanks\nwww.technicalkeeda.com");
//        mailService.sendEmail(mail);
//		세션 생성
		HttpSession session = req.getSession();
//		GET 방식으로, URL 파라미터 값 가져오기
		String UserId = req.getParameter("UserId");
//		세션 정보에 저장된 파일경로, 파일이름 가져오기
		String fileName = (String) session.getAttribute("fileName");
		String filePath = (String) session.getAttribute("filePath");

//		메일 전송
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

//			메일 설정 부분
			messageHelper.setFrom("leekh.developer@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo("leekh.developer@gmail.com"); // 받는사람 이메일
			messageHelper.setSubject(UserId + "님의 트레이너 인증요청입니다."); // 메일제목은 생략이 가능하다
			messageHelper.setText("관리자 페이지에서 승인해주세요."); // 메일 내용

//			파일 첨부 부분
//			파일 경로 설정. (로컬 경로 사용을 위함)
			FileSystemResource fsr = new FileSystemResource(filePath);
//			메일 첨부파일 설정
			messageHelper.addAttachment(fileName, fsr);
			
//			메일 전송 수행
			mailSender.send(message);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "redirect:/";
//		System.out.println(fileName);
//		System.out.println(filePath);

//		mailTo.setAddress("leekh.developer@gmail.com");
//		mailTo.setFromAddress("leekh.developer@gmail.com");
//		mailTo.setTitle("Test");
//		mailTo.setMessage("Test!");
//		mailTo.setFileName(fileName);
//		mailTo.setFilePath(filePath);
//		
//		mailService.sendMail(mailTo);
		
	}

	//	트레이너 인증 대기 목록 페이지 (관리자 페이지)
	@RequestMapping("/admin_accept")
	public String adminAccept(Model model, HttpServletRequest req) throws Exception {
		logger.info("list");
//		UserType이 2인 목록만 리스트에 저장 후, model에  memberList로 저장
		List<MemberVO> memberList = service.trainerAcceptWait();
		model.addAttribute("memberList", memberList);

//		주소창에서 /admin_accept 입력시 접근 불가(메인페이지로 되돌림)
		if (req.getHeader("REFERER") == null) {
			return "redirect:/index2";
		} else {
//		주소창에서 /admin_accept 입력하지 않았을 때
			return "admin_accept";
		}
	}
	
	//	트레이너 인증 대기 목록 중, 승인 요청시, UserType을 1로 변경.
	@RequestMapping(value="/trainer_access_process", method = RequestMethod.POST)
	public String trainerAccessProcess(Model model, HttpServletRequest req) throws Exception {
//		주소창에서 /admin_accept 입력시 접근 불가(메인페이지로 되돌림)
		if (req.getHeader("REFERER") == null) {
			return "redirect:/index2";
		} else {
//			주소창에서 /admin_accept 입력하지 않았을 때
//			GET 방식으로 값 가져오기.
			String UserId = (String)req.getParameter("UserId");
//			UserType 1로 변경하는, DB UPDATE문 수행
			service.trainerAcceptComplete(UserId);
			
			return "redirect:/admin_accept";
		}
	}

	
}
