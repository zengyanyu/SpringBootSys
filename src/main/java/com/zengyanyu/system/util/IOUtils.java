/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * IO工具类
 * @author zengyanyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IOUtils {

    /**
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static ByteArrayResource inputStreamToByteArrayResource(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return new ByteArrayResource(byteArrayOutputStream.toByteArray());
    }
}
