package kr.ac.ajou.mydictionary.dictionarymanager;

import org.springframework.stereotype.Component;

//@Component
public class LoginBean {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
