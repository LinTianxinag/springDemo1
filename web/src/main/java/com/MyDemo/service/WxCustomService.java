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
        return  "SUCCESS";

//        if (checkSigature(signature, timestamp, nonce, echostr)){
//            logger.info("true ,check for wx");
//            return echostr;
//        }else{
//            logger.info("false,check for wx");
//            return "";
//        }
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

//    public static void main(String[] args) {
//        System.out.println(new WxCustomService().checkSigatureout(
//                "12","12","12","34"
//        ));
//    }

}
