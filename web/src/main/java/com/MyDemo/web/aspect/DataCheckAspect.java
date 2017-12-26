package com.MyDemo.web.aspect;

import com.MyDemo.bean.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2017/12/19.
 * time:${time}
 */
@Order(2)
@Aspect
public class DataCheckAspect {
    @Pointcut("@annotation(com.MyDemo.web.Interface.DataCheck)")
    public void aspect() {
    }
//    在这里进行相关代码的处理
    @Before("aspect()")
    public void before(JoinPoint point) throws IOException, ServletException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse res = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        User user = (User) point.getArgs()[0];
        if(user==null||user.getId()==null){
            System.out.println("数据检查不通过");
//            res.sendRedirect("/login");
        }
    }
}
