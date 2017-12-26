package com.MyDemo.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2017/12/26.
 * time:${time}
 */
@Component
public class JmsSender {
    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    /**
     * 发送消息队列
     * @param queueName
     * @param message
     */
    public void send(String queueName, final Serializable message){
        jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(message);
            }
        });
    }

}
