package com.MyDemo.Util;


import com.sun.tools.javac.util.Pair;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

public class StringUtil {
    /**
     * 拼接 queryString
     * 使用utf-8编码进行 url encode, params中的Object使用简单的toString
     * @param baseUrl 基础url
     * @param params 需要拼接的参数，如果有blank的参数，会自动忽略
     */
    public static String getQueryString(String baseUrl, Map<String, Object> params) {
//        if (params == null) {
//            return baseUrl;
//        }
//
//        params = params.entrySet().stream()
//                .filter(e -> null != e.getValue())
//                .filter(e -> StringUtils.isNotBlank(e.getKey()) && StringUtils.isNotBlank(e.getValue().toString()))
//                .map(e -> {
//
//                    String key = e.getKey();
//                    String value = e.getValue().toString();
//
//                    try {
//                        key = URLEncoder.encode(key, "UTF-8");
//                        value = URLEncoder.encode(value, "UTF-8");
//                    } catch (UnsupportedEncodingException e1) {
//                        // ignore
//                        // UTF-8 should be supported, if not will use the original value
//                    }
//                    return Pair.of(key, value);
//                })
//                .collect(Collectors
//                        .toMap(Pair.,
//                                Pair::getRight,
//                                (v1,v2)->v1));
//
//        String join = Joiner.on('&')
//                .withKeyValueSeparator("=")
//                .join(params);

//        return baseUrl + "?" + join;
        return baseUrl + "?";

    }

    public static String getQueryString(String baseUrl, Object bean) {
//        Map<String, Object> map = bean2Map(bean);
//        return getQueryString(baseUrl, map);
        return null;
    }

}
