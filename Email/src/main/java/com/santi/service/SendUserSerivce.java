package com.santi.service;

import com.santi.pojo.SendUser;

public interface SendUserSerivce {
	/**
	 * 接收发送的消息到数据库
	 * @return
	 */
	int receiveSendUserMsg(SendUser sendUser);
	
	
}
