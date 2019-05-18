package com.bing.controller;

import com.bing.util.RedisLockService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Desc BaseController
 * @Author Wisty
 * @Date 2018年10月25日
 */
public class BaseController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private RedisLockService redisLockService;
	
	/**
	 * 全局用户唯一凭证
	 */
	public String getLoginId(){
		String loginId = request.getHeader("loginId");
		return loginId;
	}

	/**
	 * 登陆终端
	 */
	public String getLoginTerminal() {
		String loginTerminal = request.getHeader("loginTerminal");
		return loginTerminal;
	}
	
	/**
	 * 会话凭证
	 */
	public String getToken(){
		String token = request.getHeader("token");
		return token;
	}
	
}
