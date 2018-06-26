package com.MyDemo.web;

import com.MyDemo.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2018/6/26.
 * time:${time}
 */
@Controller
public class WXController {

    @RequestMapping(value = "/wx")
    @ResponseBody
    public String home(ModelMap map, User user){
        return "hello, this is wx view";
    }
}
