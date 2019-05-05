package com.bhyx.lizard.core.config;

import com.bhyx.lizard.core.adapter.AaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/5
 * Time: 11:45
 * To change this template use File | Setting | File Template.
 **/
@Configuration
public class AaTestConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(setTestSecurityInterceptor());
    }

    @Bean
    public AaAdapter setTestSecurityInterceptor() {
        return new AaAdapter();
    }
}
