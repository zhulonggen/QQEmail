package com.santi.service;

import java.util.List;

import com.santi.pojo.ReceiveUser;

public interface ReceiveService {

	
	/**
	 * 写入接收数据
	 * @return
	 */
	boolean addReceiveUser(ReceiveUser receiveUser);
	
	/**
	 * 向前端展示数据
	 * @param userId
	 * @return
	 */
	List<ReceiveUser> showReceiveUser();
	
	/**
	 * 删除数组里的id
	 * @param ids
	 * @return
	 */
	int removeEmail(int [] ids);
	
	/**
	 * 查询总邮件数
	 */
	int totalEmail();
	
	
}
