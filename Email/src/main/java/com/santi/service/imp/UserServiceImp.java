package com.santi.service.imp;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.santi.mapper.SendUserMapper;
import com.santi.mapper.UserMapper;
import com.santi.pojo.SendUser;
import com.santi.pojo.User;
import com.santi.service.UserSerivce;
import com.santi.util.BaseMybatis;

public class UserServiceImp implements UserSerivce {
	private SqlSessionFactory sessionFactory = null;

	/**
	 * 通过用户邮箱查找是否存在用户
	 * 
	 * @param name
	 * @return
	 */
	public User queryUserByEmail(String email, String pwd) {
		sessionFactory = BaseMybatis.getsqlSessionFactory();
		SqlSession session = sessionFactory.openSession(true);
		UserMapper userMapper = session.getMapper(UserMapper.class);
		User user = userMapper.queryUserByEmail(email, pwd);
		return user;
	}

	/**
	 * 注册用户
	 */

	@Override
	public int registUser(User u) {
		sessionFactory = BaseMybatis.getsqlSessionFactory();
		SqlSession session = sessionFactory.openSession(true);
		UserMapper userMapper = session.getMapper(UserMapper.class);
		int result = userMapper.registUser(new User(u.getUserEmail(), u.getUserPwd()));
		return result;
	}

	/**
	 * 写入第三方密码
	 */
	@Override
	public int inTreePwd(String userTreePwd, Integer userId) {
		sessionFactory = BaseMybatis.getsqlSessionFactory();
		SqlSession session = sessionFactory.openSession(true);
		UserMapper userMapper = session.getMapper(UserMapper.class);
		int i = userMapper.inTreePwd(userTreePwd, userId);
		return i;
	}

	@Override
	public User byKeyGetEmail(Integer userId) {
		sessionFactory = BaseMybatis.getsqlSessionFactory();
		SqlSession session = sessionFactory.openSession(true);
		UserMapper userMapper = session.getMapper(UserMapper.class);

		User user = userMapper.byKeyGetEmail(userId);
		return user;
	}
}
