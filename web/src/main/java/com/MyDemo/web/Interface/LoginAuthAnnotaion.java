package com.MyDemo.web.Interface;

import java.lang.annotation.*;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2017/11/1.
 * time:${time}
 */
@Documented //支持JavaDoc文档注释
@Inherited
@Target(ElementType.METHOD)//表明该注解对成员方法起作用
@Retention(RetentionPolicy.RUNTIME)//在编译以后仍然起作用
public @interface LoginAuthAnnotaion {
    boolean validate() default true;
}
