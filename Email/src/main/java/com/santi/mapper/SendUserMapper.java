package com.santi.mapper;

import java.util.List;

import com.santi.pojo.SendUser;

public interface SendUserMapper {
	/**
	 * 查询所有发送信息
	 * @return
	 */
	List<SendUser> selectAll();
	
	/**
	 * 接收发送的消息到数据库
	 * @return
	 */
	int receiveSendUserMsg(SendUser sendUser);
	
}
