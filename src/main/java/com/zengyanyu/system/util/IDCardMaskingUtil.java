package com.zengyanyu.system.util;

/**
 * 身份证马赛克工具类
 */
public class IDCardMaskingUtil {

    private IDCardMaskingUtil(){
    }

    /**
     * 马赛克
     *
     * @param idCard 身份证号码
     * @return
     */
    public static String maskIdCard(String idCard) {
        if (idCard == null || idCard.length() < 7) {
            // 如果身份证号长度不足，直接返回原字符串
            return idCard;
        }
        StringBuilder sb = new StringBuilder(idCard);
        for (int i = 6; i < sb.length() - 4; i++) {
            sb.setCharAt(i, '*');
        }
        return sb.toString();
    }

}
