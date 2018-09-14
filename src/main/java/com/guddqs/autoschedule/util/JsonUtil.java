package com.guddqs.autoschedule.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * @author wq
 */
public class JsonUtil {
    /**
     * 对象转换成JSON字符串
     *
     * @param obj 需要转换的对象
     * @return 对象的string字符
     */
    public static String objToJson(Object obj) {
        JSONObject jSONObject = JSONObject.fromObject(obj);
        return jSONObject.toString();
    }

    /**
     * JSON字符串转换成对象
     *
     * @param jsonString 需要转换的字符串
     * @param type       需要转换的对象类型
     * @return 对象
     */
    public static <T> T jsonToBean(String jsonString, Class<T> type) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        return jsonToBean(jsonObject, type);
    }

    /**
     * jsonObject 转换为javabean
     *
     * @param jsonObject
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToBean(JSONObject jsonObject, Class<T> type) {
        return (T) JSONObject.toBean(jsonObject, type);
    }

    /**
     * json 转换为 beanList
     *
     * @param jsonArray
     * @param type
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List jsonToBeanList(JSONArray jsonArray, Class type) {
        List<Object> list = new ArrayList<Object>();
        for (Object obj : jsonArray) {
            if (obj instanceof JSONArray) {
                list.add(jsonToBeanList((JSONArray) obj, type));
            } else if (obj instanceof JSONObject) {
                list.add(jsonToBean((JSONObject) obj, type));
            } else {
                list.add(obj);
            }
        }
        return list;
    }

    public static List jsonToBeanList(String json, Class type) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        return jsonToBeanList(jsonArray, type);
    }

    /**
     * 将JSONArray对象转换成list集合
     *
     * @param jsonArr
     * @return
     */
    public static List<Object> jsonToMapList(JSONArray jsonArr) {
        List<Object> list = new ArrayList<Object>();
        for (Object obj : jsonArr) {
            if (obj instanceof JSONArray) {
                list.add(jsonToMapList((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                list.add(jsonToMap((JSONObject) obj));
            } else {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 将json字符串转换成map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        return jsonToMap(obj);
    }

    /**
     * 将JSONObject转换成map对象
     */
    public static Map<String, Object> jsonToMap(JSONObject obj) {
        Set<?> set = obj.keySet();
        Map<String, Object> map = new HashMap<String, Object>(set.size());
        for (Object key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONArray) {
                map.put(key.toString(), jsonToMapList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                map.put(key.toString(), jsonToMap((JSONObject) value));
            } else {
                map.put(key.toString(), obj.get(key));
            }
        }
        return map;
    }
}