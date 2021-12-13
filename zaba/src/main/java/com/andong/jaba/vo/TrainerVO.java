package com.andong.jaba.vo;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrainerVO implements Serializable{
	private String TrainerUserId;
	private String Career;
	private String KeyCode;
	
	/*
	public String getTrainerUserId() {
		return TrainerUserId;
	}
	public void setTrainerUserId(String userId) {
		TrainerUserId = userId;
	}
	public String getCareer() {
		return Career;
	}
	public void setCareer(String career) {
		Career = career;
	}
	public String getKeyCode() {
		return KeyCode;
	}
	public void setKeyCode(String keyCode) {
		KeyCode = keyCode;
	}
	*/
}
