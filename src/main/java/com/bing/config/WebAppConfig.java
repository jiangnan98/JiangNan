package com.bing.config;

import com.bing.adapter.AuthSecurityInterceptor;
import com.bing.adapter.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author 拦截器注册
 *
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport{

	@Resource
	private MyInterceptor myInterceptor;


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("拦截器启动");
		registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/templates/**")
				.addResourceLocations("classpath:/templates/");
		super.addResourceHandlers(registry);
	}
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("拦截器启动");
		registry.addInterceptor(myInterceptor).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
