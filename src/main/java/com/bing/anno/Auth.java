package com.bing.anno;

import java.lang.annotation.*;

/**
 * @author 权限注解
 *
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Auth {

    public String name() default "";
}
