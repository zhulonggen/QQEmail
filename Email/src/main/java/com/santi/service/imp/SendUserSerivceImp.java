package com.santi.service.imp;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.santi.service.SendUserSerivce;
import com.santi.mapper.SendUserMapper;
import com.santi.pojo.SendUser;
import com.santi.util.BaseMybatis;

public class SendUserSerivceImp implements SendUserSerivce{
	private SqlSessionFactory sessionFactory = null;
	
	@Override
	public int receiveSendUserMsg(SendUser sendUser) {
		sessionFactory = BaseMybatis.getsqlSessionFactory();
		SqlSession session = sessionFactory.openSession(true);
		SendUserMapper mapper = session.getMapper(SendUserMapper.class);
//		new一个空对象来接收时间
		int i = mapper.receiveSendUserMsg(sendUser);
		return i;
	}
}
