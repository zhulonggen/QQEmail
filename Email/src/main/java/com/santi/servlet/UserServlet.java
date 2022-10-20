package com.santi.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSON;
import com.santi.constants.ApplicationConstants;
import com.santi.enums.UserEnum;
import com.santi.pojo.User;
import com.santi.service.UserSerivce;
import com.santi.service.imp.UserServiceImp;
import com.santi.util.BaseServlet;

@WebServlet("/userServlet")
public class UserServlet extends BaseServlet{
	
	private UserSerivce userService=new UserServiceImp();
	
	public void isRegist(HttpServletRequest req,HttpServletResponse resp) throws IOException {
//		从前端接收用户email判断是否存在
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		
		User user = userService.queryUserByEmail(email,pwd);
		
		if(user!=null) {
			req.getSession().setAttribute(ApplicationConstants.USER_KEY,user.getUserId());
			resp.getWriter().write(JSON.toJSONString(UserEnum.SUCCEE.getNumber()));
		}else {
			resp.getWriter().write(JSON.toJSONString(UserEnum.ERROR.getNumber()));
		}
	}
	
	public void addUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		String code = req.getParameter("code");
		//从session域获取验证码
		String captcha = (String)req.getSession().getAttribute("captcha");
		
		//先判断该用户有没有注册
		User user= userService.queryUserByEmail(email,pwd);
		if(user==null&&captcha.equals(code)) {
			User u = new User(email,pwd);
			int i = userService.registUser(u);//注册用户返回结果
			if(i>0) {//判断是否注册成功
				resp.getWriter().write(JSON.toJSONString(UserEnum.SUCCEE.getNumber()));
			}else {
				resp.getWriter().write(JSON.toJSONString(UserEnum.ERROR.getNumber()));
			}
		}else {
			resp.getWriter().write(JSON.toJSONString(UserEnum.EXITS.getNumber()));
		}
	}
}
