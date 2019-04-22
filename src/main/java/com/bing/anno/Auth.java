package com.bing.anno;

import java.lang.annotation.*;

/**
 * @author 登录访问权限注解
 *
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    public String name() default "";
}
