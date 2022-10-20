package com.santi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.santi.constants.ApplicationConstants;

@WebFilter({"/emailServlet","/pages/*","/index.html"})
public class LoginFilter implements Filter{

	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		
		Integer key = (Integer)req.getSession().getAttribute(ApplicationConstants.USER_KEY);
		System.out.println(key);
		if(key!=null) {
			chain.doFilter(request, response);//放行
		}else {
			HttpServletResponse resp=(HttpServletResponse)response;
//			重定向
			resp.sendRedirect(req.getContextPath()+"/user/login.html");
		}
	}

	@Override
	public void destroy() {
		
	}
	
}
