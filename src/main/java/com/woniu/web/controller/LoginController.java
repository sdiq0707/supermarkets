package com.woniu.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woniu.domain.Userinfo;
import com.woniu.service.IUserinfoService;

@RestController
@RequestMapping("/users")
public class LoginController {
	
	@Autowired
	private IUserinfoService userService;
	
	@PostMapping("/login")
	public Map<String,Object> login(String username, String password) {
		
		Subject subject = SecurityUtils.getSubject();
		if((username!=null) && (password!=null)) {
			System.out.println(username+":"+password);
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		Map<String,Object> map = new HashMap<>();
		
		String path = "";
		try {
			subject.login(token);
			path = "/admin/index.html";
			map.put("status", 200);	//成功码
			map.put("msg", "登录成功");
			map.put("path", path);	//跳转路径
			map.put("user", subject.getPrincipal());
		} catch (AuthenticationException e) {
			path = "/index.html";
			map.put("status", 500);	//失败码
			map.put("msg", "登录异常");
			map.put("path", path);	//跳转路径
		}
		
		return map;
	}
	
	@PostMapping("logout")
	public Map<String,Object> logout() {
		Subject subject = SecurityUtils.getSubject();
		Map<String,Object> map = new HashMap<>();
		subject.logout();
		map.put("status", !subject.isAuthenticated()? 200 : 500);
		map.put("msg", "登出");
		map.put("path", "/index.html");	//跳转路径
		return map;
	}
	
	@PostMapping("/register")
	public Map<String,Object> register(String username, String password) {
		
		System.out.println(username+":"+password);
		
		// 加密
		String salt = UUID.randomUUID().toString().replaceAll("-", "");
		SimpleHash sh = new SimpleHash("MD5", password, salt, 1024);
		Userinfo user = new Userinfo();
		user.setUsername(username);
		user.setPasswd(sh.toHex());
		user.setSalt(salt);
		
		userService.save(user);
		
		Map<String,Object> map = new HashMap<>();
		map.put("path", "/index.html");		//注册完去登录页面
		map.put("status", 200);
		map.put("msg", "注册成功");
		
		return map;
	}

}
