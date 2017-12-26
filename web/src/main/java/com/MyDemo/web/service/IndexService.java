package com.MyDemo.web.service;


import com.MyDemo.bean.User;
import com.MyDemo.mapper.UserMapper;
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

    @DataCheck
    public String Index(User user){
        if(user!=null&&user.getId()!=null)
        userMapper.insert(user);
        return "add a new user";
    }


}
