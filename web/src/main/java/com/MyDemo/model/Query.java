package com.MyDemo.model;

/**
 * desciper:常用的一些查询的变量值
 * Created by LinTianxiang.
 * Date: 2017/12/29.
 * time:${time}
 */
public class Query {
    private String key;
    private String value;
    private String name;
    private String password;
    private Integer expire;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getExpire() {
        return expire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }
}
