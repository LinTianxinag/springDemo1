package com.MyDemo.service;

import com.MyDemo.Util.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

@Service
public class WxManagerSuccess {
    static final Logger logger = LogManager.getLogger(WxManagerSuccess.class);

    private static Calendar cal = Calendar.getInstance();
    private static final String APPID = "wxe64c8ab684d32b63";
    private static final String APPSECRET = "5ae5091484c016ba274ab8d96d9b285c";
    private static final String tokenName = "access_token_wx";
    private String getValue = null;
//    {
//        System.out.println("-------the wxServer start, get token right now---------");
//        logger.info("-------the wxServer start, get token right now---------");
//        accessToken ();
//    }

    public static String getAccessToken() throws Exception {
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + APPID + "&secret=" + APPSECRET;
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

    //    获取accessToken 并保存到redis当中。每间隔15分钟检查一次，请求得到的token是2h有效时间
//    剩余半个小时就重新获取
//    @Scheduled(fixedRate = 60*1000)
    @Scheduled(cron = "0 */15 * * * ?")
    public void accessToken () {
        getValue = RedisUtil.get(tokenName);
        Long ttl =0l;
        if (getValue != null){
            ttl = RedisUtil.ttl(tokenName);
            if (ttl > 30l){
                logger.info("token time left:"+ttl);
                return;
            }
        }
        try {
            getValue = getAccessToken();
            RedisUtil.set("access_token_wx", getValue);
            RedisUtil.expire("access_token_wx",2*60*60);
            WxCustomService.access_token_wx=getValue;//将值放入类的volatile 修饰的变量中
            logger.info("token reset --------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }


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
