package com.zengyanyu.system.util;

import java.util.Map;
import java.util.StringJoiner;

public class MapToStringUtil {

    private MapToStringUtil() {
    }

    public static String mapToString(Map<String, Object> map) {
        StringJoiner joiner = new StringJoiner("&");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            // 确保值不为 null
            if (entry.getValue() != null) {
                // 将键和值转换为字符串，并处理可能的 URL 编码
                String key = entry.getKey();
                String value = String.valueOf(entry.getValue());
                // 注意：这里没有进行 URL 编码，可能需要根据实际情况处理
                joiner.add(key + "=" + value);
            }
        }
        return joiner.toString();
    }
}
