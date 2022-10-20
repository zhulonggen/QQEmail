package com.santi;

import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.santi.mapper.ReceiveMapper;
import com.santi.mapper.SendUserMapper;
import com.santi.mapper.UserMapper;
import com.santi.pojo.ReceiveUser;
import com.santi.pojo.SendUser;
import com.santi.pojo.User;
import com.santi.service.SendUserSerivce;
import com.santi.service.UserSerivce;
import com.santi.service.imp.SendUserSerivceImp;
import com.santi.service.imp.UserServiceImp;
import com.santi.util.BaseMybatis;

public class Test {

	@org.junit.Test
	public void t() throws ParseException {
		SqlSessionFactory sessionFactory = BaseMybatis.getsqlSessionFactory();
		SqlSession session = sessionFactory.openSession(true);
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		User u = mapper.byKeyGetEmail(5);
		
		System.out.println(u.getUserEmail());
	}
}
