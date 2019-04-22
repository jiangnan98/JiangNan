package com.bing.anno;

import java.lang.annotation.*;

/**
 * @author  参数校验注解
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Verify {
	boolean require() default true; // 是否必须
	
}
