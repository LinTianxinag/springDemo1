package com.MyDemo.web.service;


import com.MyDemo.bean.User;
import com.MyDemo.mapper.UserMapper;
import com.MyDemo.support.JmsQueueLisnter;
import com.MyDemo.support.JmsSender;
import com.MyDemo.web.Interface.DataCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @DataCheck
    public String Index(User user){
        if(user!=null&&user.getName()!=null)
        userMapper.insertSelective(user);
        return "add a new user";
    }

    public String sendJmsMessage(String message){
        jmsSender.send("queue1",message);
        return "ok";
    }




}
