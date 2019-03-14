package com.MyDemo.Util;

import com.alibaba.fastjson.JSONObject;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

public class ResultUtil {
    public Map<Object, Object> map = new HashMap<>();

    public  ResultUtil Add(Object key, Object value){
        map.put(key,value);
        return this;

    }
    public  Object toJSON(){
        return JSONObject.toJSON(map);

    }
    public ResultUtil(){
        map = new HashMap<>();
    }

}
