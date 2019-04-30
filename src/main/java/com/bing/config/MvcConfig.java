package com.bing.config;

import com.bing.adapter.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/30
 * Time: 15:38
 * To change this template use File | Setting | File Template.
 **/
public class MvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private MyInterceptor myInterceptor;
}
