package com.santi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santi.pojo.User;

public interface UserMapper {
	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> getAll();
	
	/**
	 * 通过用户邮箱查找是否存在用户
	 * @param name
	 * @return
	 */
	User queryUserByEmail(@Param("userEmail")String email,@Param("userPwd") String pwd);
	
	/**
	 * 注册用户
	 * @param u
	 * @return
	 */
	int registUser(User u);
	
	/**
	 * 根据用户id存入第三方密码
	 * @param userID
	 * @return
	 */
	int inTreePwd(@Param("userTreePwd") String userTreePwd,@Param("userId") Integer userId);
	
	/**
	 * 通过id(key)来查找对应eamil
	 */
	User byKeyGetEmail(@Param("userId") Integer userId);
}
