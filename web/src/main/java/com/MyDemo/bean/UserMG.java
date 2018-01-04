package com.MyDemo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ren on 2017/6/2.
 */
@Document(collection="user")
public class UserMG implements Serializable {
    @Id
    private String _id;
    private Long id;                //用户ID
    private String mobile;          //手机
    private Long investcounts;      //购买次数
    private Long angCash;           //
    private String idcard;          //身份证
    private String nick;            //昵称
    private String pwd;             //用户密码
    private String pwd2;            //确认密码
    private String realname;        //姓名
    private String route;           //终端
    private String ip;              //
    private String tradepwd;        //交易密码
    private Date regdate;           //注册时间
    private String openid;          //
    private Long integral;          //积分
    private Integer realfrequency;  //实名认证次数
    private String addr;            //地址
    private Long sex;               //性别
    private String oldmob;          //
    private Long relationid;        //
    private Long hadIntegral;       //
    private String refer;           //推荐人ID
    private String fno;
    private String channel;         //推广渠道
    private String subchannel;      //推广子渠道
    private String customer;
    private String taskId;
    private String head;            //头像地址
    private Date realtime;          //实名时间
    private Long binkCardAuthQuency;
    private Long userid;            //用户ID
    private Long accountid;         //账户ID
    private String com;
    private Long label;
    private Long value;
    private String lastModel;
    private String regModel;
//    private String email;           //邮箱
//    private String emailurl;        //邮箱验证地址
//
//
    private String validcode;       //验证码
//
//
    private String imagecode;       //图片验证码

    public String getValidcode() {
        return validcode;
    }

    public void setValidcode(String validcode) {
        this.validcode = validcode;
    }

    public String getImagecode() {
        return imagecode;
    }

    public void setImagecode(String imagecode) {
        this.imagecode = imagecode;
    }

    public String getRegModel() {
        return regModel;
    }

    public void setRegModel(String regModel) {
        this.regModel = regModel;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getInvestcounts() {
        return investcounts;
    }

    public void setInvestcounts(Long investcounts) {
        this.investcounts = investcounts;
    }

    public Long getAngCash() {
        return angCash;
    }

    public void setAngCash(Long angCash) {
        this.angCash = angCash;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTradepwd() {
        return tradepwd;
    }

    public void setTradepwd(String tradepwd) {
        this.tradepwd = tradepwd;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    public Integer getRealfrequency() {
        return realfrequency;
    }

    public void setRealfrequency(Integer realfrequency) {
        this.realfrequency = realfrequency;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getOldmob() {
        return oldmob;
    }

    public void setOldmob(String oldmob) {
        this.oldmob = oldmob;
    }

    public Long getRelationid() {
        return relationid;
    }

    public void setRelationid(Long relationid) {
        this.relationid = relationid;
    }

    public Long getHadIntegral() {
        return hadIntegral;
    }

    public void setHadIntegral(Long hadIntegral) {
        this.hadIntegral = hadIntegral;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getFno() {
        return fno;
    }

    public void setFno(String fno) {
        this.fno = fno;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSubchannel() {
        return subchannel;
    }

    public void setSubchannel(String subchannel) {
        this.subchannel = subchannel;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Date getRealtime() {
        return realtime;
    }

    public void setRealtime(Date realtime) {
        this.realtime = realtime;
    }

    public Long getBinkCardAuthQuency() {
        return binkCardAuthQuency;
    }

    public void setBinkCardAuthQuency(Long binkCardAuthQuency) {
        this.binkCardAuthQuency = binkCardAuthQuency;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public Long getLabel() {
        return label;
    }

    public void setLabel(Long label) {
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLastModel() {
        return lastModel;
    }

    public void setLastModel(String lastModel) {
        this.lastModel = lastModel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "UserMG{" +
                "_id='" + _id + '\'' +
                ", id=" + id +
                ", mobile='" + mobile + '\'' +
                ", investcounts=" + investcounts +
                ", angCash=" + angCash +
                ", idcard='" + idcard + '\'' +
                ", nick='" + nick + '\'' +
                ", pwd='" + pwd + '\'' +
                ", pwd2='" + pwd2 + '\'' +
                ", realname='" + realname + '\'' +
                ", route='" + route + '\'' +
                ", ip='" + ip + '\'' +
                ", tradepwd='" + tradepwd + '\'' +
                ", regdate=" + regdate +
                ", openid='" + openid + '\'' +
                ", integral=" + integral +
                ", realfrequency=" + realfrequency +
                ", addr='" + addr + '\'' +
                ", sex=" + sex +
                ", oldmob='" + oldmob + '\'' +
                ", relationid=" + relationid +
                ", hadIntegral=" + hadIntegral +
                ", refer='" + refer + '\'' +
                ", fno='" + fno + '\'' +
                ", channel='" + channel + '\'' +
                ", subchannel='" + subchannel + '\'' +
                ", customer='" + customer + '\'' +
                ", taskId='" + taskId + '\'' +
                ", head='" + head + '\'' +
                ", realtime=" + realtime +
                ", binkCardAuthQuency=" + binkCardAuthQuency +
                ", userid=" + userid +
                ", accountid=" + accountid +
                ", com=" + com +
                ", label=" + label +
                ", value=" + value +
                ", lastModel='" + lastModel + '\'' +
                ", regModel='" + regModel + '\'' +
                ", validcode='" + validcode + '\'' +
                ", imagecode='" + imagecode + '\'' +
                '}';
    }

    public static UserMG registerUserMG(User users){
        UserMG user = new UserMG();
        user._id = "";
        user.id = 0l;         //user.id
        user.idcard = "";
        user.mobile = "";
        user.nick = "";
        user.pwd = "";
        user.pwd2="";
        user.realname = "";
        user.route = "";
        user.ip="";
        user.tradepwd = "";
        user.regdate = new Date();
        user.openid = "";
        user.integral = 0l;
        user.realfrequency = 0;
        user.binkCardAuthQuency = 0l;
        user.addr = "";
        user.sex = 0l;
        user.oldmob="";
        user.relationid = 0l;
        user.hadIntegral = 0l;
        user.investcounts = 0l;   //购买次数
        user.refer = "";            //邀请用户id
        user.fno = "";             //邀请码
        user.channel = "";
        user.subchannel = "";
        user.customer = "";
        user.taskId = "";
        user.head = "";
        user.lastModel="";
        user.regModel="";
//        user.set_id(users.get_id());
//        user.setMobile(users.getMobile());
//        user.setPwd(users.getPwd());
//        user.setPwd2(users.getPwd2());
//        user.setId(users.getId());
//        user.setRoute(users.getRoute()+"");
        return user;
    }
}
