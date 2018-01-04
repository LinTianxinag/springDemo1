package com.MyDemo.web.service;


import com.MyDemo.Util.RedisUtil;
import com.MyDemo.bean.User;
import com.MyDemo.bean.UserMG;
import com.MyDemo.mapper.UserMapper;
import com.MyDemo.model.Query;
import com.MyDemo.support.JmsQueueLisnter;
import com.MyDemo.support.JmsSender;
import com.MyDemo.web.Interface.DataCheck;
import org.apache.logging.log4j.core.util.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2017/11/1.
 * time:${time}
 */
@Service
public class IndexService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    JmsSender jmsSender;
    @Autowired
    MongoTemplate mongoTemplate;

    @DataCheck
    public String Index(User user){
        if(user!=null&&user.getName()!=null)
        userMapper.insertSelective(user);
        return "add a new user";
    }

    /**
     * 使用jms消息队列
     * @param message
     * @return
     */
    public String sendJmsMessage(String message){
        jmsSender.send("queue1",message);
        return "ok";
    }
    /**
     * 使用jms消息队列
     * @param query
     * @return
     */
    public List<UserMG> mongoDBData(Query query){
        org.springframework.data.mongodb.core.query.Query query1=new org.springframework.data.mongodb.core.query.Query();
        query1.addCriteria(Criteria.where("sex").is(0));
//        query1.with(new Sort(new Sort.Order(Sort.Direction.DESC ,"regdate")));
        List<UserMG> list=mongoTemplate.find(query1.skip(1*2).limit(2),UserMG.class);
//        List<UserMG> list=mongoTemplate.find(new org.springframework.data.mongodb.core.query.Query(Criteria.where("id").is(394005)), UserMG.class);
        return list;
    }
    /**
     * shiro 简单使用
     * @param query
     * @return
     */
    public Object shiroAsk(Query query){
            //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
            //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
            SecurityUtils.setSecurityManager(securityManager);
            //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(query.getName(), query.getPassword());
            try {
                //4、登录，即身份验证
                subject.login(token);
                System.out.println("用户成功进行登录");
            } catch (AuthenticationException e) {
                System.out.println("用户没有成功登录");
                //5、身份验证失败
            }
//            Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
            //6、退出
            subject.logout();

        return "shiroAsk is Over";
    }

    /**
     * 使用redis进行缓存
     * @param query
     * @return
     */
    public void redisStorage(Query query){
        RedisUtil.set(query.getKey(),query.getValue());
        RedisUtil.expire(query.getKey(),query.getExpire());
        String getValue=null;
        while (true){
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getValue=RedisUtil.get(query.getKey());
            if(getValue!=null){
                System.out.println("还存在:"+getValue);
            }else{
                System.out.println("过期");
                break;
            }
        }
    }




}
