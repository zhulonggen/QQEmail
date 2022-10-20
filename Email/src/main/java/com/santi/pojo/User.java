package com.santi.pojo;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1463022708244357666L;
	private Integer userId;
	private String userEmail;
	private String userPwd;
	private String userTreePwd;

	public User() {
		super();
	}

	public User(Integer userId, String userEmail, String userPwd, String userTreePwd) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.userTreePwd = userTreePwd;
	}

	public User(Integer userId, String userEmail, String userPwd) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserTreePwd() {
		return userTreePwd;
	}

	public void setUserTreePwd(String userTreePwd) {
		this.userTreePwd = userTreePwd;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userEmail=" + userEmail + ", userPwd=" + userPwd + ", userTreePwd="
				+ userTreePwd + "]";
	}

	public User(String userEmail, String userPwd) {
		this.userEmail = userEmail;
		this.userPwd = userPwd;
	}

}
