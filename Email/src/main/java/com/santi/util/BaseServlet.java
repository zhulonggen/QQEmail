package com.santi.util;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("action");
		try {
			// 获取 action 业务鉴别字符串，获取相应的业务 方法反射对象
			Method method = this.getClass().getDeclaredMethod(name, HttpServletRequest.class,
					HttpServletResponse.class);
			// 调用目标业务 方法
			Object invoke = method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
