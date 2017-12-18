package com.MyDemo.web.service;

import com.MyDemo.mapper.UserMapper;
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
    public String Index(){
        return "hello";
    }
}
