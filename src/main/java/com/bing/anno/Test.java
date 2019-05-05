package com.bhyx.lizard.core.anno;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/5
 * Time: 11:49
 * To change this template use File | Setting | File Template.
 **/
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    public String name() default "";
}
