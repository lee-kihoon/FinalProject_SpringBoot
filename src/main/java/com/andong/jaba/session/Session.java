package com.andong.jaba.session;

import lombok.AllArgsConstructor;


public class Session {
	private String UserId;
	private String KeyCode;
	
	public String getKeyCode() {
		return KeyCode;
	}

	public void setKeyCode(String keyCode) {
		KeyCode = keyCode;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

}
