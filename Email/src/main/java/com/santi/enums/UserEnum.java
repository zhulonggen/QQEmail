package com.santi.enums;

public enum UserEnum {
	
	SUCCEE(200,"成功"),
	FAILURE(100,"失败"),
	ERROR(201,"用户名或密码错误"),
	NOEXITS(0,"用户不存在"),
	EXITS(1,"用户已存在"),
	
	;
	
	private Integer number;
	private String msg;
	
	private UserEnum(Integer number, String msg) {
		this.number = number;
		this.msg = msg;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
