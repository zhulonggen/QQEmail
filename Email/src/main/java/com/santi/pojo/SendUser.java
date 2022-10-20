package com.santi.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SendUser {

	private Integer sendId;
	private String sendEmail;
	private String sendTitle;
	private Date sendDate;
	private String sendContent;
	private Integer userId;



	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	
	public SendUser(String sendEmail, String sendTitle, String sendContent) {
		super();
		this.sendEmail = sendEmail;
		this.sendTitle = sendTitle;
		this.sendContent = sendContent;
	}

	public SendUser(String sendEmail, String sendTitle, Date sendDate, String sendContent) {
		super();
		this.sendEmail = sendEmail;
		this.sendTitle = sendTitle;
		this.sendDate = sendDate;
		this.sendContent = sendContent;
	}

	public String getSendTitle() {
		return sendTitle;
	}

	public void setSendTitle(String sendTitle) {
		this.sendTitle = sendTitle;
	}

	public SendUser(Integer sendId, String sendEmail, String sendTitle, Date sendDate, String sendContent) {
		super();
		this.sendId = sendId;
		this.sendEmail = sendEmail;
		this.sendTitle = sendTitle;
		this.sendDate = sendDate;
		this.sendContent = sendContent;
	}

	public SendUser() {
		super();
	}


	public Integer getSendId() {
		return sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}


	public Date getSendDate() throws ParseException {
//		处理时间
		Calendar c = Calendar.getInstance();
		sendDate = c.getTime();
		return sendDate;
	}


	public SendUser(String sendEmail, String sendTitle, Date sendDate, String sendContent, Integer userId) {
		super();
		this.sendEmail = sendEmail;
		this.sendTitle = sendTitle;
		this.sendDate = sendDate;
		this.sendContent = sendContent;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SendUser [sendId=" + sendId + ", sendEmail=" + sendEmail + ", sendTitle=" + sendTitle + ", sendDate="
				+ sendDate + ", sendContent=" + sendContent + ", userId=" + userId + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	
}
