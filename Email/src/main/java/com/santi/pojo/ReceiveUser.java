package com.santi.pojo;

import java.util.Calendar;
import java.util.Date;

public class ReceiveUser {

	

	public ReceiveUser(Integer receiveId, String receiveEmail, String receiveTitle, String receiveContent,
			Date receiveDate) {
		super();
		this.receiveId = receiveId;
		this.receiveEmail = receiveEmail;
		this.receiveTitle = receiveTitle;
		this.receiveContent = receiveContent;
		this.receiveDate = receiveDate;
	}

	public ReceiveUser(String receiveEmail, String receiveTitle, String receiveContent, Date receiveDate,
			Integer userId) {
		super();
		this.receiveEmail = receiveEmail;
		this.receiveTitle = receiveTitle;
		this.receiveContent = receiveContent;
		this.receiveDate = receiveDate;
		this.userId = userId;
	}

	public ReceiveUser() {
		super();
	}

	public String getReceiveTitle() {
		return receiveTitle;
	}

	public void setReceiveTitle(String receiveTitle) {
		this.receiveTitle = receiveTitle;
	}

	public ReceiveUser(Integer receiveId, String receiveEmail, Date receiveDate, Integer userId) {
		super();
		this.receiveId = receiveId;
		this.receiveEmail = receiveEmail;
		this.receiveDate = receiveDate;
		this.userId = userId;
	}

	private Integer receiveId;
	private String receiveEmail;
	private String receiveTitle;
	
	

	public String getReceiveContent() {
		return receiveContent;
	}

	public void setReceiveContent(String receiveContent) {
		this.receiveContent = receiveContent;
	}

	private String receiveContent;
	private Date receiveDate;
	private Integer userId;

	public Integer getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}

	public String getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public Date getReceiveDate() {
//		处理时间
		Calendar c = Calendar.getInstance();
		receiveDate = c.getTime();
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ReceiveUser [receiveId=" + receiveId + ", receiveEmail=" + receiveEmail + ", receiveTitle="
				+ receiveTitle + ", receiveContent=" + receiveContent + ", receiveDate=" + receiveDate + ", userId="
				+ userId + "]";
	}

}
