package com.MyDemo.web;



import com.MyDemo.bean.User;
import com.MyDemo.model.Query;
import com.MyDemo.web.Interface.LoginAuthAnnotaion;
import com.MyDemo.web.service.IndexService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String home(ModelMap map, User user){
        map.put("name","lin");
        map.put("age",18);
        map.put("childhood",8);
        map.put("serviceWord",indexService.Index(user));
        logger.info("------mainController的输出日志info");
        logger.info("------git 使用测试   ");
        logger.error("------mainController的输出日志error");
        return "index";
    }

    /**
     * 消息队列
     * @param map
     * @param queueMessage
     * @return
     */
    @RequestMapping(value = "/jmsQueueMessage")
    public String jmsQueueMessage(ModelMap map, String queueMessage){
        map.put("message",indexService.sendJmsMessage(queueMessage));
        return "index";
    }
      /**
     * 消息队列
     * @param map
     * @param query
     * @return
     */
    @RequestMapping(value = "/mongoDBData")
    public String mongoDBData(ModelMap map, Query query){
        map.put("mongoDBData",indexService.mongoDBData(query));
        return "index";
    }

      /**
     * 消息队列
     * @param map
     * @param query
     * @return
     */
    @RequestMapping(value = "/shiroAsk")
    @ResponseBody
    public Object shiroAsk(ModelMap map, Query query){
        return indexService.shiroAsk(query);
    }


        /**
     * redis缓存使用
     * @param map
     * @param query
     * @return
     */
    @RequestMapping(value = "/redisStorage")
    @ResponseBody
    public String redisStorage(ModelMap map, Query query){
        new Thread(new Runnable(){
            public void run() {
                System.out.println("Override runnable run!");
            }
        }){
            public void run() {
                //super.run();
                System.out.println("Override Thread run!");
                indexService.redisStorage(query);
            }
        }.start();

        return "ok,redis operation finished";
    }



    @RequestMapping(value = "/login")
    @LoginAuthAnnotaion
    public String login(ModelMap map){
        return "login";
    }

}
