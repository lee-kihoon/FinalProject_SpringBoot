package com.andong.jaba.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// Valid 애너테이션 관련 링크
// https://goodteacher.tistory.com/262
public class MemberVO implements Serializable {
	@NotBlank(message = "ID를 입력해주세요.")
	@Pattern(regexp="[a-zA-Z1-9]{6,12}", message="영어와 숫자로 포함 6~15자리 이내로 입력해주세요.")
	private String UserId;
	
	@Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "영어와 숫자로 포함 6~12자리 이내로 입력해주세요.")
	private String Password;
	
	@Pattern(regexp="^[가-힣a-zA-Z]*$", message = "이름을 확인해주세요.")
	private String Name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private LocalDate Birth;
	
	@Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$", message = "'000-0000-0000' 형식으로 입력해주세요.")
	private String Phone;
	
	@NumberFormat(style = Style.NUMBER)
	private double Height;
	private double Weight;
	
	private int UserType;
	

}
