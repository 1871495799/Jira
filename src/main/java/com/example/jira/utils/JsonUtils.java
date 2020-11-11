package com.example.jira.utils;


import com.alibaba.fastjson.JSON;

/**
 * @Description Json工具类
 * @author wangzs12
 * @date 2019/11/15
 */
public class JsonUtils {

    // private static Gson gs = new Gson();

    /**
     * @Description 将对象转换为Json串
     * @param object
     * @return
     * @author wangzs12
     * @date 2019/11/15
     */
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }



    /**
     * @Description 将Json串转换为指定对象
     * @param <T>
     * @param jsonString
     * @param clazz
     * @return
     * @author wangzs12
     * @date 2019/11/15
     */
    public static <T> T getObjectFromJson(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * @Description 将Json串转换为指定对象
     * @param jsonString
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @author wangzs12
     * @date 2019/11/15
     */
    public static Object getObjectFromJson(String jsonString, String className) throws ClassNotFoundException {
        return JSON.parseObject(jsonString, Class.forName(className));
    }
}
