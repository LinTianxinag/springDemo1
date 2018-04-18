package com.MyDemo.web.service;


import com.MyDemo.Util.RedisUtil;
import com.MyDemo.bean.MobileModelMG;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        if(user!=null&&user.getRealname()!=null)
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
        mongoTest();
        return list;
    }

    /**
     * 插入mongo的数据
     */
    public  void mongoTest(){
        String actionsJs = "D:\\workspace1\\template\\mobileList.txt";
        String type="华为";
        MobileModelMG mg = new MobileModelMG();
        MobileModelMG mg1 = new MobileModelMG();
        org.springframework.data.mongodb.core.query.Query query1=new org.springframework.data.mongodb.core.query.Query();
        try {
            FileInputStream inputstream = new FileInputStream(actionsJs);
            String line; // 用来保存每行读取的内容
            BufferedReader bufferreader = new BufferedReader(new InputStreamReader(
                    inputstream,"utf-8"));
            line = bufferreader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                if(line.trim().endsWith(":")){
                    type=line.substring(0,line.length()-1);
                    System.out.println("type: "+type);
                }else if(line.indexOf("end")>0){
                    System.out.println("end is Detected");
                }else if(type!=null&&line.length()>0){
                    dealLine(line,type,mg,mg1,query1);
                }
                line = bufferreader.readLine(); // 读取下一行
            }
//		 将读到 buffer 中的内容写出来
            inputstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public  void dealLine(String line,String type,MobileModelMG mg,MobileModelMG mg1,org.springframework.data.mongodb.core.query.Query query1){
        String[] strs,strs2;
        String model="";
        List<String> mobileModels=new ArrayList<>(),collectModels=new ArrayList<>();
        switch (type){
            case "华为":
                strs=line.split(" ");
                model=strs[0];
                mobileModels.add(strs[strs.length-1]);
                System.out.println(model+'|'+mobileModels);
                break;
            case "苹果":
                strs=line.split("：");
                model=strs[0];
                if(strs[1]!=null){
                    strs2=strs[1].split("、");
                    for(String s:strs2){
                        mobileModels.add(s);
                    }

                }
                System.out.println(model+'|'+mobileModels);
                break;
            case "vivo":
                strs=line.split("：");
                model=strs[0];
                mobileModels.add(strs[strs.length-1]);
                System.out.println(model+'|'+mobileModels);
                break;
            case "Oppo":
                strs=line.split("：");
                model=strs[0];
                mobileModels.add(strs[strs.length-1]);
                System.out.println(model+'|'+mobileModels);
                break;
            case "三星":
                strs=line.split("：");
                model=strs[0].replace("三星","").trim();
                mobileModels.add(strs[strs.length-1]);
                System.out.println(model+'|'+mobileModels);
                break;
            default:
                break;
        }
        for (String s:mobileModels) {
            query1=new org.springframework.data.mongodb.core.query.Query();
            query1.addCriteria(Criteria.where("models").is(s.trim()));
            mg1=mongoTemplate.findOne(query1,MobileModelMG.class);
            if(mg1==null){
                mg.set_id(createID());
                mg.setModel(model);
                mg.setType(type);
                mg.setModels(s.trim());
                mongoTemplate.save(mg,"mobileModel");
            }

        }
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

//java8 新的东西的集合测试和使用
    public static void main(String[] args) {
        System.out.println(createID());
    }
    public static String createID() {
        Date date = new Date();
        return String.valueOf(date.getTime());
    }


}
