package com.MyDemo.service;

import com.MyDemo.Util.RedisUtil;
import com.MyDemo.Util.ResultUtil;
import com.MyDemo.bean.User;
import com.MyDemo.mapper.UserMapper;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * this service is usef for wx service
 */
@Service
public class WxCustomService {
    static final Logger logger = LogManager.getLogger(WxCustomService.class);
    private static final String tokenName = "access_token_wx";
    private static final String wx_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    public static volatile String access_token_wx;//这个值暂时先不用吧
    public static  String access_token;//

    @Autowired
    UserMapper userMapper;

    public Object wxCustomDeal(HttpServletRequest request,
                               HttpServletResponse response, String signature, String timestamp, String nonce, String echostr){
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        response.setCharacterEncoding("UTF-8");
        HashMap<String, Object> resultMap = new HashMap<>();
        access_token = RedisUtil.get(tokenName);
        try {
            access_token = (access_token==null)?WxManagerSuccess.getAccessToken():access_token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("sendMsgSever get the token: "+access_token +"------------");
        try {
            ServletInputStream stream = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = new String("");
            while((line = reader.readLine()) != null){
                buffer.append(line);
            }
            JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
            logger.info(jsonObject.toString());
            //resultMap.put("ToUserName",jsonObject.getString("ToUserName"));
            //resultMap.put("FromUserName","S______A");
            //resultMap.put("CreateTime", (new  Date()).getTime());
            // resultMap.put("MsgType","transfer_customer_service");
            //String json = JSON.toJSONString(resultMap);
            //JSONObject result = JSONObject.parseObject(json);
            if (jsonObject.getString("MsgType").equals("text")) { //收到的是文本消息

                //也回复一个文本消息
                logger.info("POST"  +  jsonObject);
                sendCustomerMessage(jsonObject.getString("FromUserName"),"hello,the text msg from server");

                return "success";
            }else{
                //也回复一个文本消息
                logger.info("POST"  +  jsonObject);
                sendCustomerMessage(jsonObject.getString("FromUserName"),"hello,the other msg from server");

                return "success";
            }
//            else if(jsonObject.getString("MsgType").equals("event")){
//                sendFirstMessage(jsonObject.getString("FromUserName"));
//                return "success";
//            } else { //那就是图片的消息了
//                //也回复一个图片消息
//                sendCustomerImageMessage(jsonObject.getString("FromUserName"), jsonObject.getString("MediaId"));
//                return "success";
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

//        if (checkSigature(signature, timestamp, nonce, echostr)){
//            logger.info("true ,check for wx");
//            return echostr;
//        }else{
//            logger.info("false,check for wx");
//            return "";
//        }
//        return checkSigatureout(signature, timestamp, nonce, echostr);
    }


    public Object wxUserAdd(HttpServletRequest request,
                                        HttpServletResponse response,String name, String mobile, Date date){
        User user = new User();
        user.setId(Math.round(Math.random() * 1000000));
        user.setRealname(name);
        user.setMobile(mobile);
        user.setRegdate(date);
        userMapper.insert(user);
        return new ResultUtil().Add("errcode",0).Add("msg","add ok").toJSON();

    }

    public Object wxUserFind(HttpServletRequest request,
                                        HttpServletResponse response, User user){
//        Map<String, Object> map  = new HashMap<>();
        JSONObject map = new JSONObject();
        map.put("result",userMapper.selectByNameMobile(user));
        map.put("errcode",0);
        map.put("msg","find ok");
        return map;

    }
    /**
     * 在开启消息推送的时候，会把客户消息发送到自己的服务器
     * 微信会发送消息来验证这个服务器是否有效，需要用到下面的验证
     * 如果成功，返回原字符串，失败，随便返回，那么无法添加你的服务器为微信的服务器
     * 1.原来的接口已经改成接受消息了
     * 2.好像网上说有个get ，post，如果两个仅仅controller 的 method 不同，
     * 是不是就可以一起在了，
     *
     * @param request
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public Object wxCustomDeal_ForCheck(HttpServletRequest request,
                               HttpServletResponse response, String signature, String timestamp, String nonce, String echostr){

        if (checkSigature(signature, timestamp, nonce, echostr)){
            logger.info("true ,check for wx");
            return echostr;
        }else{
            logger.info("false,check for wx");
            return "";
        }
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
            //            tmpStr = new String(digest);
            tmpStr = byteToStr(digest);
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
//            tmpStr = new String(digest);
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        System.out.println(tmpStr);
        return tmpStr;
    }

    /**
     53      * 将字节加工然后转化成字符串
     54      * @param digest
     55      * @return
     56      */
     private static String byteToStr(byte[] digest){
                 String strDigest="";
                 for(int i=0;i<digest.length;i++){
                         //将取得字符的二进制码转化为16进制码的的码数字符串
                         strDigest+=byteToHexStr(digest[i]);
                     }
                 return strDigest;
             }
    /**
              67      * 把每个字节加工成一个16位的字符串
              68      * @param b
              69      * @return
              70      */
    public static String byteToHexStr(byte b){
                //转位数参照表
                 char[] Digit= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


                 //位操作把2进制转化为16进制
                char[] tempArr=new char[2];
                 tempArr[0]=Digit[(b>>>4)&0X0F];//XXXX&1111那么得到的还是XXXX
                 tempArr[1]=Digit[b&0X0F];//XXXX&1111那么得到的还是XXXX

                 //得到进制码的字符串
                 String s=new String(tempArr);
                 return s;
             }

    /**
     * 服务端发送客服消息
     * access_token=ACCESS_TOKEN
     * @return
     * @throws Exception
     */
    public static void sendMsg(String msgType, String text) throws Exception {
        String token = RedisUtil.get(tokenName);
/**
 * {
 "touser": "OPENID",
 "msgtype": "text",
 "text": {
 "content": "Hello World"
 }
 }
 */
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?"+
                "access_token="+token+"&touser="+token+"&msgtype="+token;

        URL url = new URL(accessTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
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
        Integer errcode = jsonObject.getInteger("errcode");
        String errmsg = jsonObject.getString("errmsg");
        if (errcode == 0){
            logger.info("fail-----:"+errmsg);
        }else{
            logger.info("success-----:"+errmsg);
        }

    }


    public static String makeTextCustomMessage(String openId,String content){
        content.replace("\"", "\\\"");
        String jsonMsg="{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
        return String.format(jsonMsg, openId,content);

    }


    public static JSONObject sendCustomerMessage(String touser,String content){
        JSONObject obj = new JSONObject();

        obj.put("touser", touser);
        obj.put("msgtype", "text");

        JSONObject text = new JSONObject();
        text.put("content", content);

        obj.put("text", text);

        logger.info("回复的文本:\n"+obj.toString());
        JSONObject jsonObject = httpsRequest(obj);

        logger.info("回复jsonObject:\n"+jsonObject);
        return jsonObject;
    }

    public static JSONObject httpsRequest(JSONObject jsonParam){
        StringBuffer sb=new StringBuffer();
        try {
            // 创建url资源
            URL url = new URL(wx_url + access_token);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            // 设置允许输入
            conn.setDoInput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接,会不会每次发送数据都会建立连接，不太明白，先注释了，后续需要再开启
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            设置连接和读的超时时间
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            // 转换为字节数组
            byte[] data = (jsonParam.toString()).getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("contentType", "application/json");
            // 开始连接请求
            conn.connect();
            OutputStream out = new DataOutputStream(conn.getOutputStream()) ;
            // 写入请求的字符串
            out.write((jsonParam.toString()).getBytes());
            out.flush();
            out.close();

            System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                System.out.println("连接成功");
                // 请求返回的数据
                InputStream in1 = conn.getInputStream();
                try {
                    String readLine=new String();
                    BufferedReader responseReader=new BufferedReader(new InputStreamReader(in1,"UTF-8"));
                    while((readLine=responseReader.readLine())!=null){
                        sb.append(readLine).append("\n");
                    }
                    responseReader.close();
                    System.out.println(sb.toString());

                } catch (Exception e1) {
                    e1.printStackTrace();
                    logger.info("error1++" + e1);
                }
            } else {
                System.out.println("error connect++");
                logger.info("error++");
            }

        } catch (Exception e) {
            logger.info("error3++" + e);
        }
        logger.info(sb+"sb");
        return JSONObject.parseObject(sb.toString());
    }

// not ok for use ,and need somet
//    public static boolean sendCustomMessage(String token,String jsonMsg){
//        boolean flag=false;
//        //String accessToken=getAccessToken("xxxx","xxxx").getToken();
//        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
//        requestUrl=requestUrl.replace("ACCESS_TOKEN", token);
//        JSONObject jsonResult=CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
//        if(jsonResult!=null){
//            int errorCode=jsonResult.getInt("errcode");
//            String errorMessage=jsonResult.getString("errmsg");
//            if(errorCode==0){
//                flag=true;
//            }else{
//                System.out.println("客服消息发送失败:"+errorCode+","+errorMessage);
//                flag=false;
//            }
//        }
//        return flag;
//    }


//    public static void main(String[] args) {
//        System.out.println(new WxCustomService().checkSigatureout(
//                "12","12","12","34"
//        ));
//    }

}
