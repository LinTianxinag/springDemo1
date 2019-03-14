package com.MyDemo.web;

import com.MyDemo.Util.ResultUtil;
import com.MyDemo.bean.User;
import com.MyDemo.service.WxCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Arrays;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2018/6/26.
 * time:${time}
 */
@Controller
public class WXController {

    @Autowired
    WxCustomService wxCustomService;

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
    @RequestMapping(value = "/wxCustomService")
    @ResponseBody
    public Object wxCustomServicePost(HttpServletRequest request,
                                      HttpServletResponse response, String signature, String timestamp, String nonce, String echostr){
        if(signature == null || timestamp == null || nonce == null){
            return "empty";
        }
        return wxCustomService.wxCustomDeal(request,response, signature, timestamp, nonce, echostr);
    }
    @RequestMapping(value = "/wxUserAdd")
    @ResponseBody
    public Object wxUserAdd(HttpServletRequest request,
                            HttpServletResponse response, String name, String mobile,@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date date ){
        if(name == null || mobile == null){
            return new ResultUtil().Add("errcode",1).Add("msg","empty value").toJSON();
        }
        return wxCustomService.wxUserAdd(request,response, name, mobile, date);
    }
    @RequestMapping(value = "/wxUserFind")
    @ResponseBody
    public Object wxUserFind(HttpServletRequest request,
                                      HttpServletResponse response, User user){
        return wxCustomService.wxUserFind(request,response, user);
    }

//    @RequestMapping(value = "/wxCustomService")
//    public Object wxCustomServiceGet(String signature, String timestamp, String nonce, String echostr){
//        if(signature == null || timestamp == null || nonce == null){
//            return "empty";
//        }
//        return wxCustomService.wxCustomDeal(signature, timestamp, nonce, echostr);
//    }


}
