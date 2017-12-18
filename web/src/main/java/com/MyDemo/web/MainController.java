package com.MyDemo.web;



import com.MyDemo.web.Interface.LoginAuthAnnotaion;
import com.MyDemo.web.service.IndexService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2017/11/1.
 * time:${time}
 */
@Controller
public class MainController {
    static final Logger logger = LogManager.getLogger(MainController.class);

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/index")
    public String home(ModelMap map){
        map.put("name","lin");
        map.put("age",18);
        map.put("childhood",8);
        map.put("serviceWord",indexService.Index());
        logger.info("------mainController的输出日志info");
        logger.error("------mainController的输出日志error");
        return "index";
    }

    @RequestMapping(value = "/login")
    @LoginAuthAnnotaion
    public String login(ModelMap map){
        return "login";
    }

}
