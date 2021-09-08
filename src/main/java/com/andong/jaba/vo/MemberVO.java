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
// Valid �ֳ����̼� ���� ��ũ
// https://goodteacher.tistory.com/262
public class MemberVO implements Serializable {
	@NotBlank(message = "ID�� �Է����ּ���.")
	@Pattern(regexp="[a-zA-Z1-9]{6,12}", message="���̵�� ����� ���ڷ� �����ؼ� 6~15�ڸ� �̳��� �Է����ּ���.")
	private String UserId;
	
	@Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "��й�ȣ�� ����� ���ڷ� �����ؼ� 6~12�ڸ� �̳��� �Է����ּ���.")
	private String Password;
	
	@Pattern(regexp="^[��-�Ra-zA-Z]*$", message = "�̸��� Ȯ�����ּ���.")
	private String Name;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Past
	private LocalDate Birth;
	
	@Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$", message = "'000-0000-0000' �������� �Է����ּ���.")
	private String Phone;
	
	@NumberFormat(style = Style.NUMBER)
	private double Height;
	private double Weight;
	
	private boolean UserType;
	
}
