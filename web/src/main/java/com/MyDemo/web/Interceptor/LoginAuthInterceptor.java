package com.MyDemo.web.Interceptor;


import com.MyDemo.web.Interface.LoginAuthAnnotaion;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project Name:dubbo-aalc
 * File Name:LoginAuthInterceptor
 * Package:com.anganglicai.core.interceptor
 *
 * @author:panwang
 * @description: 登录权限拦截器
 * @date:2017/6/4
 * @version:V1.0
 * @see:jdk7 Copyright (c) 2017, mikuismywifu@gmail.com All Rights Reserved.
 */
public class LoginAuthInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在Controller被调用前，先执行，可以在这里执行一些安全检查
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("pre 开始进行之前的处理");
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            LoginAuthAnnotaion loginAuth =((HandlerMethod) handler).getMethodAnnotation(LoginAuthAnnotaion.class);
            // 没有声明需要权限,或者声明不验证权限
            if (loginAuth == null || loginAuth.validate() == false) {
                return true;
            } else {
                return true;
//                String path = request.getContextPath();
//                //根据session获取redis中用户id
//                String userid = RedisUtil.get(Constants.WEB_SESSION + request.getSession().getId());
//                //用户未登录
//                if (userid == null || StringUtils.isBlank(userid)) {
//                    String url = request.getRequestURI();
//                    if (!WebUtil.isPermitUrl("member/login", url)) {
//                        // 取得参数内容
//                        String queryString = request.getQueryString();
//                        String reurl = path + "/member/login?redirect=" + URLEncoder.encode(XSSRenderTools.cleanXSS(request.getRequestURL() + "?" + request.getQueryString()), "utf-8");
//                        if (StringUtils.isNotBlank(queryString))
//                            response.sendRedirect(reurl);
//                        else {
//                            reurl = path + "/member/login?redirect=" + URLEncoder.encode(XSSRenderTools.cleanXSS(request.getRequestURL().toString()), "utf-8");
//                            response.sendRedirect(reurl);
//                        }
//                        return false;
//                    }
//                }else {
//                    return true;
//                }
//                return false;
            }
        } else
            return true;
    }

    /**
     * 在Controller调用全部完成后执行，如果ex变量不为空，表示有异常了，这里可以记录异常日志
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // TODO Auto-generated method stub
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * Controller调用后执行，这时，可以修改ModelAndView，比如转到其它view之类
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }
}
