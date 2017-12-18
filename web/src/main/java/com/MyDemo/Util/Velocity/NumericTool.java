package com.MyDemo.Util.Velocity;

import org.apache.velocity.tools.config.DefaultKey;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2017/11/1.
 * time:${time}
 */
@DefaultKey("numeric")
public class NumericTool {
    /**
     * 百分比计算，保留截取整数部分
     */
    public static String getInte(BigDecimal b1, BigDecimal b2, int len){
        DecimalFormat df = new DecimalFormat("0.0000"); // 保留几位小数
        DecimalFormat dif = new DecimalFormat("0.00"); // 保留几位小数
        String xx = df.format(b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue());
        Double it = Double.parseDouble(xx);
        String item = dif.format(it * 100);
        String okc = item.substring(0,item.indexOf("."));
        return okc;
    }
    /**
     * 将用户的身份证号进行暗码替换
     */
    public static String Hideidcard(Object o){
        String it=(String)o;//转换用户的身份证
        if(it==null || it.equals("")){
            return "";
        }
        String idcard = it.replaceAll("(\\d{3})(.+)(\\w{3})", "$1***********$3");
        return  idcard;
    }
    /**
     * 将用户的手机号隐藏中间部分
     */
    public static String Hidemobile(Object o){
        String it=(String)o;//转换用户的手机号
        if(it == null || it.equals("")){
            return "";
        }
        String mobile = it.replaceAll("(\\d{3})\\d{6}(\\d{2})", "$1******$2");
        return  mobile;
    }

    /**
     * 将用户的手机号隐藏中间部分
     */
    public static String Hidemobiles(Object o){
        String it=(String)o;//转换用户的手机号
        if(it == null || it.equals("")){
            return "";
        }
        String mobile = it.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
        return  mobile;
    }


    /**
     * 将用户的手机号隐藏中间部分
     */
    public static String hideBankCardNo(Object o){
        String it=(String)o;//转换用户的手机号
        String mobile = it.replaceAll("(\\d{3})\\d*(\\d{2})", "$1******$2");
        return  mobile;
    }

    /**
     * 取银行卡后四位
     * @param o
     * @return
     */
    public static String tilBankCardNo(Object o){
        String it=(String)o;
        String mobile = it.substring(it.length()-4);
        return  mobile;
    }


    /**
     * 利率计算，保留两位小数
     */
    public static String TofixedRate(BigDecimal d){
        DecimalFormat df = new DecimalFormat("0.0000"); // 保留几位小数
        DecimalFormat dif = new DecimalFormat("0.00"); // 保留几位小数
        String xx = df.format(d);
        Double it = Double.parseDouble(xx);
        String item = dif.format(it * 100);
        return item;
    }
    /**
     * 利率数值转换，保留整数部分
     */
    public static String TofixedRateV2(BigDecimal d){
        DecimalFormat df = new DecimalFormat("0.0000"); // 保留几位小数
        DecimalFormat dif = new DecimalFormat("0"); // 保留几位小数
        String xx = df.format(d);
        Double it = Double.parseDouble(xx);
        String item = dif.format(it * 100);
        return item;
    }



    /**
     * 金额运算保留整数
     */
    public static String ToFixedItInt(BigDecimal d){
        DecimalFormat df = new DecimalFormat("0"); // 保留整数\
        if (d == null)
            return "";
        return df.format(d);
    }

    /**
     * 金额运算保留整数,类型为String
     */
    public static String ToFixString2Int(String d){
        DecimalFormat df = new DecimalFormat("0");
        return d;
    }
    /**
     * 将浮点数小数，固定保留两位小数
     */
    public static String toFixedCurren(BigDecimal d) {
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        if (d == null)
            return "";
        return df.format(d);
    }

    /**
     * 保留三位小数点
     *
     * @param d
     * @return
     */
    public static String toFixedMoney(Object d) {
        DecimalFormat df = null;
        if (d == null)
            return "";
        Object d1 = Double.parseDouble(d.toString());
        try {
            String s = d.toString();
            s = s.substring(s.lastIndexOf(".") + 1, s.length());

            if (s.length() >= 3) {
                s = s.substring(s.length() - 1);
                if (Integer.parseInt(s) != 0) {
                    df = new DecimalFormat("0.000"); // 保留几位小数
                    return df.format(d1);
                }
            }
            df = new DecimalFormat("0.00"); // 保留几位小数
            return df.format(d1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 计算折扣，固定保留一位小数
     */
    public static String toFixedDiscount(Double d) {
        DecimalFormat df = new DecimalFormat("0.0"); // 保留几位小数
        if (d == null)
            return "";
        return df.format(d * 10);
    }

    /**
     * 将浮点数小数，固定保留两位小数
     */
    public static String toFixedCurren(Object d) {
        DecimalFormat    df   = new DecimalFormat("0.00"); // 保留几位小数
        if (d == null)
            return "";
        return df.format(d);
    }

    public static String toFixedCurren(String d) {

        DecimalFormat    df   = new DecimalFormat("0.00"); // 保留几位小数
        if (d == null)
            return "";
        return df.format(Double.parseDouble(d));
    }

    public static String add(double d1, double d2) { // 进行加法运算
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return df.format(b1.add(b2).doubleValue());
    }

    public static String sub(double d1, double d2) { // 进行减法运算
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return df.format(b1.subtract(b2).doubleValue());
    }

    public static String subV2(Object d1, Object d2) { // 进行减法运算
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        BigDecimal b1 = (BigDecimal)d1;
        BigDecimal b2 = (BigDecimal)d2;
//		BigDecimal b2 = new BigDecimal(d2);
        return df.format(b1.subtract(b2).doubleValue());
//		return null;
    }


    public static String mul(double d1, double d2) { // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        Object dou = b1.multiply(b2).doubleValue();
        String s = dou.toString();
        s = s.substring(s.lastIndexOf(".") + 1, s.length());
        DecimalFormat df = null;
        if (s != null && s.length() >= 3) {
            s = s.substring(s.length() - 1);
            if (Integer.parseInt(s) != 0) {
                df = new DecimalFormat("0.000"); // 保留几位小数
                return df.format(dou);
            }
            df = new DecimalFormat("0.00"); // 保留几位小数
        } else {
            df = new DecimalFormat("0.00"); // 保留几位小数
        }
        return df.format(dou);
    }

    public static String mul(String d1, String d2) { // 进行乘法运算
        if (d1 == null || d2 == null)
            return "";
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return df.format(b1.multiply(b2).doubleValue());
    }

    public static String mul(Object d1, Object d2) { // 进行乘法运算
        if (d1 == null || d2 == null)
            return "";
        BigDecimal b1 = new BigDecimal(d1.toString());
        BigDecimal b2 = new BigDecimal(d2.toString());
        Object dou = b1.multiply(b2).doubleValue();
        String s = dou.toString();
        s = s.substring(s.lastIndexOf(".") + 1, s.length());
        DecimalFormat df = null;
        if (s != null && s.length() >= 3) {
            s = s.substring(s.length() - 1);
            if (Integer.parseInt(s) != 0) {

                df = new DecimalFormat("0.000"); // 保留几位小数
                return df.format(dou);
            }
            df = new DecimalFormat("0.00"); // 保留几位小数
        } else {
            df = new DecimalFormat("0.00"); // 保留几位小数
        }
        return df.format(dou);
    }

    public static String div(double d1, double d2, int len) {// 进行除法运算
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return df.format(b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    /**
     * 获取百分数
     * @param b1
     * @param b2
     * @param len
     * @return
     */
    public static String DivDD(BigDecimal b1,BigDecimal b2,int len) {
        if (b1 == null || b2 == null){
            return null;
        }
        DecimalFormat df = new DecimalFormat("0.0000"); // 保留几位小数
        DecimalFormat dif = new DecimalFormat("0.00"); // 保留几位小数
        String xx = df.format(b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue());
        Double it = Double.parseDouble(xx);
        String item = dif.format(it * 100);
        return item;
    }

    public static String DivDD2(BigDecimal b1,BigDecimal b2,int len) {
        DecimalFormat df = new DecimalFormat("0.0000"); // 保留几位小数
        DecimalFormat dif = new DecimalFormat("0"); // 保留几位小数
        BigDecimal b3 = BigDecimal.valueOf(100);
        b1 = b1.multiply(b3);
        String xx = df.format((b1).divide(b2,len).doubleValue());
        Double it = Double.parseDouble(xx);
        String item = dif.format(it);
        return item;
    }

    public static String checkDate(Date data){
        Date date1 = new Date();
        if (data.getTime() < date1.getTime()){
            return "1";
        }else {
            return "0";
        }
    }



    public static String addThem(BigDecimal d1, BigDecimal d2) { // 进行加法运算
        if (d1==null || d2 == null){
            return null;
        }
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        return df.format(d1.add(d2).doubleValue());
    }

    public static BigDecimal subThem(BigDecimal d1, BigDecimal d2) { // 进行减法运算
        if (d1==null || d2 == null){
            return null;
        }
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        String result = removeTheZero(df.format(d1.subtract(d2).doubleValue()));
        return new BigDecimal(result);
    }

    public static String subtoGet(BigDecimal d1, BigDecimal d2) { // 进行减法运算
        if (d1 == null || d2 == null){
            return "0";
        }
        DecimalFormat df = new DecimalFormat("0"); // 保留几位小数
        return df.format(d1.subtract(d2).doubleValue());
    }


    /**
     * 加法改变
     */
    public static String subThemit(BigDecimal d1, BigDecimal d2) { // 进行加法运算
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        BigDecimal a1=d1.abs();
        BigDecimal a2=d2.abs();
        return df.format(a1.add(a2).doubleValue());
    }

    /**
     * 加法计算
     * 5个参数
     */
    public static String addAll5(BigDecimal b1,BigDecimal b2,BigDecimal b3,BigDecimal b4,BigDecimal b5){
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        if(b1==null|b2==null|b3==null|b4==null|b5==null)return "0.00";
        BigDecimal a1=b1.abs();
        BigDecimal a2=b2.abs();
        BigDecimal a3=b3.abs();
        BigDecimal a4=b4.abs();
        BigDecimal a5=b5.abs();
        return df.format(a1.add(a2).add(a3).add(a4).add(a5).doubleValue());
    }

    /**
     * 加法计算
     * 4个参数
     */
    public static String addAll4(BigDecimal b1,BigDecimal b2,BigDecimal b3,BigDecimal b4){
        DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
        if(b1==null|b2==null|b3==null|b4==null)return "0.00";
        BigDecimal a1=b1.abs();
        BigDecimal a2=b2.abs();
        BigDecimal a3=b3.abs();
        BigDecimal a4=b4.abs();
        return df.format(a1.add(a2).add(a3).add(a4).doubleValue());
    }



    /**
     * author:Ren
     * 去掉小数点后面多余的0
     * @param o
     * @return
     */
    public static String removeTheZero(Object o){
        String s = String.valueOf(o);
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;

    }

    /**
     * 除法
     * @param s1
     * @param s2
     * @param scale
     * @return
     */
    public static String divs(String s1,String s2,int scale){
        BigDecimal b1 = new BigDecimal(Double.parseDouble(s1));
        BigDecimal b2 = new BigDecimal(Double.parseDouble(s2));
        return String.valueOf(b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue());
    }
//	public static void main(String[] args) {
//		System.out.println(divs(new BigDecimal(2),new BigDecimal(10),2));
//	}
    /**
     * 日期：增加天数
     * @param times
     * @param day
     * @return
     */
    public static Date addDays(String times,Integer day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        time = sdf.parse(times);
        System.out.println(time);
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(time);
        calendar.add(calendar.DATE,day);
        time = calendar.getTime();
        return time;
    }
    /**
     * 截取字符串的首位到18位
     * APP端，helps/notice使用
     */
    public static String sub_zero_seventeen(String str) {
        return str.substring(0,17);
    }

    public static String dateFormat(Date date1,String format){
        String datestr = null;
        if (date1 == null){
            return null;
        }else {
            try {
                SimpleDateFormat sdf= new SimpleDateFormat(format);
                datestr = sdf.format(date1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return datestr;
        }
    }

    public static String commonFormat(String type, Integer value){

        return null;
    }
}
