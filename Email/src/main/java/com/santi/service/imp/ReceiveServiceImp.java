package com.santi.service.imp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.santi.mapper.ReceiveMapper;
import com.santi.pojo.ReceiveUser;
import com.santi.service.ReceiveService;
import com.santi.util.BaseMybatis;

public class ReceiveServiceImp implements ReceiveService {

	@Override
	public boolean addReceiveUser(ReceiveUser receiveUser) {
		SqlSessionFactory sessionFactory = BaseMybatis.getsqlSessionFactory();
		SqlSession session = sessionFactory.openSession(true);
		ReceiveMapper mapper = session.getMapper(ReceiveMapper.class);

		int i = mapper.addReceiveUser(receiveUser);

		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ReceiveUser> showReceiveUser() {
		SqlSessionFactory sessionFactory = BaseMybatis.getsqlSessionFactory();
		SqlSession session = sessionFactory.openSession(true);

		ReceiveMapper mapper = session.getMapper(ReceiveMapper.class);
		List<ReceiveUser> userList = mapper.showReceiveUser();
		
		return userList;
	}

	@Override
	public int removeEmail(int[] ids) {
		SqlSessionFactory sessionFactory = BaseMybatis.getsqlSessionFactory();
		SqlSession session = sessionFactory.openSession(true);

		ReceiveMapper mapper = session.getMapper(ReceiveMapper.class);
		int i = mapper.removeEmail(ids);
		return i;
	}
	
	@Override
	public int totalEmail() {
		SqlSessionFactory sessionFactory = BaseMybatis.getsqlSessionFactory();
		SqlSession session = sessionFactory.openSession(true);
		
		ReceiveMapper mapper = session.getMapper(ReceiveMapper.class);
		int i = mapper.totalEmail();
		return i;
	}
}
