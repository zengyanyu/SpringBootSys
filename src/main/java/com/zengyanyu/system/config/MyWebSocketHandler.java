package com.zengyanyu.system.config;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author zengyanyu
 */
public class MyWebSocketHandler extends TextWebSocketHandler {

    /**
     * 连接建立后触发
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("客户端连接成功: " + session.getId());
        session.sendMessage(new TextMessage("欢迎连接到WebSocket服务器!"));
    }

    /**
     * 处理文本消息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("收到消息: " + payload);

        // 简单回声处理
        session.sendMessage(new TextMessage("服务器回复: " + payload));
    }

    /**
     * 连接关闭后触发
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("客户端断开连接: " + session.getId());
    }

    /**
     * 传输错误处理
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("传输错误: " + exception.getMessage());
        session.close(CloseStatus.SERVER_ERROR);
    }

}
