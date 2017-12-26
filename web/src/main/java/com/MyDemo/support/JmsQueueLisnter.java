package com.MyDemo.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.HashMap;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2017/12/26.
 * time:${time}
 */
@Service
public class JmsQueueLisnter implements MessageListener{

    @Override
    public void onMessage(Message message) {
        try {
//            HashMap<String,String> querys = JSON.parseObject((String) ((ObjectMessage) message).getObject(), HashMap.class);
            System.out.println((String) ((ObjectMessage) message).getObject());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
