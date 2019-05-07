package com.bing;

import com.bing.adapter.CorsFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan("com.bing.mapper")
@SpringBootApplication
@ServletComponentScan
public class JiangNanApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiangNanApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean setFilter() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new CorsFilter());
        filterBean.setName("CorsFilter");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }

}
