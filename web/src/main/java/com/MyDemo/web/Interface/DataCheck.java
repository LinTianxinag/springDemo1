package com.MyDemo.web.Interface;

import java.lang.annotation.*;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2017/12/19.
 * time:${time}
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataCheck {
}
