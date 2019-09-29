package com.woniu.logAop;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.woniu.domain.Tlog;
import com.woniu.service.ITlogService;

/*
 * * 日志切面类
 */

@Aspect
@Component
public class LogAspect {
	
	@Autowired
	private ITlogService service;
	
	// controller切入点
	@Pointcut("execution(public * com.woniu.web.controller..*.*(..))"+
			"&& !execution(public * com.woniu.web.controller.TlogController.*(..))")
	public void controllerCut() {} 	
	
	// serviceImpl切入点
	@Pointcut("execution(public * com.woniu.service.impl..*.*(..))"+
			"&& !execution(public * com.woniu.service.impl.TlogServiceImpl.save(..))")
	public void serviceCut() {} 	
	
	
	@Around("serviceCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("切面around增强织入开始.....");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		// 角色 用户
		Integer roleId = 1001;
		Integer userId = 2002;
		String userName = request.getRemoteUser();
		// 日志类型
		String strType = "info";
		// 类名
		String className = pjp.getSignature().getDeclaringTypeName();
		String strClassName = className.substring(className.lastIndexOf(".")+1);
		// 方法名
		String methodName = pjp.getSignature().getName();
		// IP地址
		String IPAddress = InetAddress.getLocalHost().toString();
		String strIPAddress = IPAddress.substring(IPAddress.lastIndexOf("/")+1);
		// 操作日期
		Date createTime = new Date();
		// 描述
		StringBuilder sb = new StringBuilder();
		String strMessage = sb.append(userName).append("执行了").append(strClassName).append("类的").append(methodName).append("方法").toString();
		
		
		//测试：====================================================
		Map<String,Object> data = new HashMap<>();
		data.put("URL", request.getRequestURL().toString());
		data.put("ClassName", pjp.getSignature().getDeclaringTypeName());
		data.put("methodName", pjp.getSignature().getName());
		data.put("ServerName", request.getServerName());
		data.put("ServerPort", request.getServerPort());
		data.put("ipAddress", InetAddress.getLocalHost().toString());
		System.out.println("map:  "+data);
		//=========================================================
		
		Object result = pjp.proceed();
		
		String msg = insertLog(strType,strIPAddress,userId,userName,methodName,createTime,strMessage,roleId);
		System.out.println("User:"+request.getRemoteUser()+"------"+msg);
		System.out.println("切面around增强织入结束.....");
		System.out.println("===============================");
		System.out.println();
		
		return result;
	}
	
	// 存入数据库
	public String insertLog(String strType,String strIPAddress, Integer userId,String userName,String methodName,Date createTime,String strMessage,Integer roleId) {
		// tlog存入数据库
		service.save(new Tlog(null,strType,strIPAddress,userId,userName,methodName,createTime,strMessage,roleId));
		return "日志保存成功！！！";
	}
	
}
