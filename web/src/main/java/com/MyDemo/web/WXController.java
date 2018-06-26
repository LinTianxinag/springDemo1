package com.MyDemo.web;

import com.MyDemo.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
    public Object home(String signature, String timestamp, String nonce, String echostr){
        String token = "3qOCpeh2vimfssLirU";
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
        return signature!=null?signature.toUpperCase().equals(tmpStr.toUpperCase()):false;
    }
}
