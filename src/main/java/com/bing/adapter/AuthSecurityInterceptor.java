package com.bing.adapter;



import com.bing.anno.Auth;
import com.bing.pack.LizardSystemCode;
import com.bing.pack.ResponseResult;
import com.bing.util.LogUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Desc 权限拦截器
 * @Author Wisty
 * @Date 2018年10月24日
 */
public class AuthSecurityInterceptor implements HandlerInterceptor {
	Logger log = LogUtils.getBussinessLogger();
	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		boolean result = false;
		log.info("进入了切面");
		if(handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			Auth auth = method.getMethod().getAnnotation(Auth.class);
			if (auth != null) {
				String loginId = request.getHeader("loginId");// 用户登录id
				String token = request.getHeader("token");
				result = false;
			} else {
				return true;
			}
			if (!result) {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().print(new Gson().toJson(new ResponseResult(LizardSystemCode.NOT_LOGIN)));
			}
		}
		return result;
	}


	@Override
	public void afterCompletion(HttpServletRequest arg0,
								HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
						   Object arg2, ModelAndView arg3) throws Exception {
	}
}
