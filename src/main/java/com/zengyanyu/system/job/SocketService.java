/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zengyanyu
 */
@Service
public class SocketService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 发送通知
    public void sendNotification(String message) {
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
