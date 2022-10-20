package com.santi.mapper;

import java.util.List;

import com.santi.pojo.ReceiveUser;

public interface ReceiveMapper {
	
	/**
	 * 写入接收数据
	 * @return
	 */
	int addReceiveUser(ReceiveUser receiveUser);
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
