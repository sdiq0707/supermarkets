package com.woniu.logInteceptor;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(MyInterceptor.class);
	
	// 在请求处理之前进行调用（Controller方法调用之前）
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse httpServletResponse, Object handler)
			throws Exception {

		System.out.println("MyInterceptor 拦截器开始.....");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = df.format(new Date());
		LOG.info(date + "---" + req.getRequestURI() + "被拦截");
		LOG.info("LocalHost: " + InetAddress.getLocalHost() + "--RemoteUser: " + req.getRemoteUser());
		
		System.out.println("MyInterceptor 拦截器结束.....");
		System.out.println();
		
		return true;
	}

	// 请求处理之后进行调用（Controller方法调用之后）
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
	}

	// 在整个请求结束之后被调用（主要是用于进行资源清理工作）
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}
}
