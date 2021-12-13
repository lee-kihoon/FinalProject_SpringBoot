package com.andong.jaba.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.andong.jaba.file.FileDto;
import com.andong.jaba.service.MemberService;
import com.andong.jaba.vo.TrainerVO;

//https://goodteacher.tistory.com/351

// 트레이너로 회원가입 진행 시 파일 업로드
@Controller
public class FileController {
	@Inject
	MemberService service;

	@RequestMapping("/file")
	public String file() {
		return "trainer_add";
	}
	
	@PostMapping("/upload")
	public String upload(TrainerVO vo,@RequestParam MultipartFile[] uploadfile, Model model, HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<FileDto> list = new ArrayList<>();
//		회원가입시, 입력한 UserId, GET방식으로 값 가져오기
		String userId = req.getParameter("UserId");
//		파일경로, 파일이름을 저장하기 위한, 변수 초기화
		String filePath = "";
		String fileName = "";
		
//		세션 생성
		HttpSession session = req.getSession();
		
//		파일 업로드 수행
		for (MultipartFile file : uploadfile) {
//			파일을 정상적으로 업로드했을경우,
			if (!file.isEmpty()) {

				FileDto fileDto = new FileDto(userId,
						file.getOriginalFilename(),
						file.getContentType() );
				list.add(fileDto);
				
				File newFileName = new File(fileDto.getUuid() + "_" + fileDto.getFileName());
				file.transferTo(newFileName);
				
				fileName = fileDto.getUuid() + "_" + fileDto.getFileName();
//				파일업로드 경로
//				로컬에서 실행 시 경로
//				C:/Test/
//				우분투 서버에서 실행 시 경로
//				/home/an/dev/java/trainerFile/
				filePath = "/Users/hyun/Desktop/down/" + fileDto.getUuid() + "_" + fileDto.getFileName();
				
//				파일 이름, 파일경로 session에 저장
				session.setAttribute("fileName", fileName);
				session.setAttribute("filePath", filePath);
				
//				System.out.println(fileName);
//				System.out.println(filePath);
//				System.out.println(vo.getTrainerUserId());
//				System.out.println(vo.getCertificationFile());
				
			}
		}
		// model.addAttribute("files", list);
		return "redirect:/mail?UserId="+userId;
	}
}
