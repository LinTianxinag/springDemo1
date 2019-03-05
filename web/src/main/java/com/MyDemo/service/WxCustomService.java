package com.MyDemo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * this service is usef for wx service
 */
@Service
public class WxCustomService {
    static final Logger logger = LogManager.getLogger(WxCustomService.class);

    public Object wxCustomDeal(String signature, String timestamp, String nonce, String echostr){
        if (checkSigature(signature, timestamp, nonce, echostr)){
            logger.info("true ,check for wx");
            return echostr;
        }else{
            logger.info("false,check for wx");
            return "falseForWx";
        }
//        return checkSigatureout(signature, timestamp, nonce, echostr);
    }

    public boolean checkSigature(String signature, String timestamp, String nonce, String echostr){
        String token = "Xiaolin2013";
        String[] strs=new String[] {token,timestamp,nonce};
        Arrays.sort(strs);
        StringBuffer content=new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            content.append(strs[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = new String(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        System.out.println(tmpStr);
        logger.info("tmpStr--------------");
        logger.info(tmpStr);
        return signature!=null?signature.toUpperCase().equals(tmpStr.toUpperCase()):false;
    }

    public Object checkSigatureout(String signature, String timestamp, String nonce, String echostr){
        System.out.println(signature +"|" +timestamp +"|" +nonce +"|"+echostr);
        String token = "Xiaolin2013";
        String[] strs=new String[] {token,timestamp,nonce};
        Arrays.sort(strs);
        StringBuffer content=new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            content.append(strs[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = new String(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        System.out.println(tmpStr);
        return tmpStr;
    }

}
