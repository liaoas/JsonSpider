package com.liao.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * <p>
 * 对象格式转换工具类
 * </p>
 *
 * @author LiAo
 * @since 2023-08-19
 */
public class ConvertUtil {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * JsonNode 转为 Map<String, Object>
     *
     * @param jsonNode 要转换的数据
     * @return 转换后的
     */
    public static Map<String, Object> JsonNodeToMapStrObj(JsonNode jsonNode) {

        return MAPPER.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * JsonNode 转为 Map<String, String>
     *
     * @param jsonNode 要转换的数据
     * @return 转换后的
     */
    public static Map<String, String> JsonNodeToMapStrStr(JsonNode jsonNode) {

        return MAPPER.convertValue(jsonNode, new TypeReference<Map<String, String>>() {
        });
    }
}