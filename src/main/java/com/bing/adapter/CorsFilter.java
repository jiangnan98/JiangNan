package com.bing.adapter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/** 
 * @Description 跨域
 * @author 刘小龙
 * @date 2017年7月12日 上午11:34:03 
 * @version V1.3.1
 */ 
@Order(1)
@WebFilter(filterName = "CorsFilter", urlPatterns = "/*")
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) req;
		HttpServletResponse httpServletResponse = (HttpServletResponse) res;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("origin"));
		httpServletResponse.setHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS, DELETE");
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Referer,User-Agent,token,loginId,loginTerminal");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(httpServletRequest, httpServletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) {
		
	}

	public void destroy() {
		
	}

}