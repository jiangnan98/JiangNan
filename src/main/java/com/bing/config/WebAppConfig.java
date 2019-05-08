package com.bing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 拦截器注册
 *
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	@Resource
	AaAdapter aaAdapter;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("加载拦截器");
		registry.addInterceptor(aaAdapter).addPathPatterns("/**");
	}

}
