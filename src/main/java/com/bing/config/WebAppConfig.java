package com.bing.config;

import com.bing.adapter.AuthSecurityInterceptor;
import com.bing.adapter.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 拦截器注册
 *
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	@Resource
	private AuthSecurityInterceptor authSecurityInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authSecurityInterceptor)
				.addPathPatterns("/**");
	}

}
