package com.zengyanyu.system.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

/**
 * JSON工具类
 */
public class JsonUtils {

    private JsonUtils() {
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 设置不区分大小写
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static JsonNode readTree(String str) throws JsonProcessingException {
        return objectMapper.readTree(str);
    }

    /**
     * JSON 转对象
     *
     * @param json  json字符串
     * @param clazz 反射对象
     * @param <T>   返回反射对象的实体类型
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    /**
     * 将JSON数组字符串转换为对象数组
     *
     * @param json  JSON数组字符串
     * @param clazz 对象类
     * @param <T>   对象类型
     * @return 对象数组
     * @throws Exception 转换过程中可能发生的异常
     */
    public static <T> T[] fromJsonArr(String json, Class<T[]> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    /**
     * 如果你想提供一个反序列化方法，它不会抛出异常，而是返回一个Optional
     *
     * @param json  json字符串
     * @param clazz 反射对象
     * @return 返回反射对象的实体类型.get()
     */
    public static <T> Optional<T> fromJsonSafe(String json, Class<T> clazz) {
        try {
            return Optional.of(objectMapper.readValue(json, clazz));
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // 或者使用日志记录
            return Optional.empty();
        }
    }

}
