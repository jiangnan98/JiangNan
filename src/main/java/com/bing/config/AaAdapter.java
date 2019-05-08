package com.bing.config;

import com.bing.anno.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/5
 * Time: 11:46
 * To change this template use File | Setting | File Template.
 **/
@Component
public class AaAdapter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Auth auth = method.getMethod().getAnnotation(Auth.class);
            System.out.println("进入切面");
            if (auth != null) {
                result = false;
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
