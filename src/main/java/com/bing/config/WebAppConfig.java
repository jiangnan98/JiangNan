package com.bing.config;

import com.bing.adapter.AuthSecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 拦截器注册
 *
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(setAuthSecurityInterceptor());
	}

	@Bean
	public AuthSecurityInterceptor setAuthSecurityInterceptor() {
		return new AuthSecurityInterceptor();
	}

}
