package com.gyuray.dto;

public class User {
	int userNum;
	String userId;
	String userName;
	String userPassword;
	String userEmail;
	String userRegDate;
	
	public User() {}
	
	public User(String userId, String userName, String userPassword, String userEmail) {
		this(-1, userId, userName, userPassword, userEmail, "");
	}
	
	public User(int userNum, String userId, String userName, String userPassword, String userEmail, String userRegDate) {
		this.userNum = userNum;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userRegDate = userRegDate;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserRegDate() {
		return userRegDate;
	}

	public void setUserRegDate(String userRegDate) {
		this.userRegDate = userRegDate;
	}
}
