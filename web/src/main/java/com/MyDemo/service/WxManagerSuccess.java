package com.MyDemo.service;

import com.MyDemo.Util.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

@Service
public class WxManagerSuccess {
    private static Calendar cal = Calendar.getInstance();
    private static final String APPID = "wxe64c8ab684d32b63";
    private static final String APPSECRET = "5ae5091484c016ba274ab8d96d9b285c";

    public static String getAccessToken() throws Exception {
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + APPID + "&secret=" + APPSECRET;
        System.out.println("URL for getting accessToken accessTokenUrl=" + accessTokenUrl);

        URL url = new URL(accessTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        //获取返回的字符
        InputStream inputStream = connection.getInputStream();
        int size = inputStream.available();
        byte[] bs = new byte[size];
        inputStream.read(bs);
        String message = new String(bs, "UTF-8");

        //获取access_token
        JSONObject jsonObject = JSONObject.parseObject(message);
        String accessToken = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");
        System.out.println("accessToken=" + accessToken);
        System.out.println("expires_in=" + expires_in);
        return accessToken;


    }

    //    获取accessToken 并保存到redis当中。每间隔90分钟请求一次，请求得到的token是2h有效时间
    @Scheduled(cron = "0 */1 * * * ?")
    public void accessToken () {
        System.out.println("check the token ------------");
        cal = Calendar.getInstance();
        System.out.println(cal.getTime());


//        RedisUtil.set("accessToken_token", "12");

    }

    public static void main(String[] args) {
        try {
            RedisUtil.set("access_token", getAccessToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RedisUtil.expire("access_token",1*60);
    }

}
