package com.bing.config;

import com.bing.adapter.FileUploadInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/30
 * Time: 14:24
 * To change this template use File | Setting | File Template.
 **/
@Configuration
public class AdapterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(securityInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public FileUploadInterceptor securityInterceptor() {
        return new FileUploadInterceptor();
    }
}
