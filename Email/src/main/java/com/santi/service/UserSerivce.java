package com.santi.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.santi.mapper.UserMapper;
import com.santi.pojo.SendUser;
import com.santi.pojo.User;
import com.santi.util.BaseMybatis;

public interface UserSerivce {

	/**
	 * 通过用户邮箱查找是否存在用户
	 * 
	 * @param name
	 * @return
	 */
	User queryUserByEmail(String email,String pwd);

	/**
	 * 注册用户
	 * 
	 * @param u
	 * @return
	 */
	int registUser(User u);
	
	/**
	 * 根据用户id存入第三方密码
	 * @param userID
	 * @return
	 */
	int inTreePwd(String userTreePwd,Integer userId);

	/**
	 * 通过id(key)来查找对应eamil
	 */
	User byKeyGetEmail(@Param("userId") Integer userId);

}
