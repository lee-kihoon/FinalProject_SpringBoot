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
// Valid ¾Ö³ÊÅ×ÀÌ¼Ç °ü·Ã ¸µÅ©
// https://goodteacher.tistory.com/262
public class MemberVO implements Serializable {
	@NotBlank(message = "ID¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.")
	@Pattern(regexp="[a-zA-Z1-9]{6,12}", message="¾ÆÀÌµğ´Â ¿µ¾î¿Í ¼ıÀÚ·Î Æ÷ÇÔÇØ¼­ 6~15ÀÚ¸® ÀÌ³»·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.")
	private String UserId;
	
	@Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "ºñ¹Ğ¹øÈ£´Â ¿µ¾î¿Í ¼ıÀÚ·Î Æ÷ÇÔÇØ¼­ 6~12ÀÚ¸® ÀÌ³»·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.")
	private String Password;
	
	@Pattern(regexp="^[°¡-ÆRa-zA-Z]*$", message = "ÀÌ¸§À» È®ÀÎÇØÁÖ¼¼¿ä.")
	private String Name;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Past
	private LocalDate Birth;
	
	@Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$", message = "'000-0000-0000' Çü½ÄÀ¸·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.")
	private String Phone;
	
	@NumberFormat(style = Style.NUMBER)
	private double Height;
	private double Weight;
	
	private boolean UserType;
	
}
