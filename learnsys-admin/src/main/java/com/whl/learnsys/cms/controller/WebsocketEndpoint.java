package com.whl.learnsys.cms.controller;

/**
 * @program: learnsys-parent
 * @description:
 * @author: whl
 * @create: 2020-03-27 13:44
 **/


import com.whl.common.listener.RedisMessageListener;
import com.whl.learnsys.cms.config.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;


/***
 * 用“@ServerEndPoint”注解来实现，实现简单；
 * 分别是用户ID 和用户订阅的主题
 */
@ServerEndpoint("/socket/{userId}/{topic}")
@RestController
public class WebsocketEndpoint {


    /***
     * 用来记录当前连接数的变量
     */
    private static volatile int onlineCount = 0;


    /***
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
     */
    private static CopyOnWriteArraySet<WebsocketEndpoint> webSocketSet = new CopyOnWriteArraySet<WebsocketEndpoint>();


    /**
     * 与某个客户端的连接会话，需要通过它来与客户端进行数据收发
     */
    private Session session;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketEndpoint.class);

    //用来引入刚才在webcoketConfig注入的类
    private RedisMessageListenerContainer container = SpringUtils.getBean("container");


    //自定义的消息发送器
    private RedisMessageListener listener;


    /***
     * socket打开的处理逻辑
     * @param session
     * @param userId
     * @param topic
     * @throws Exception
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId, @PathParam("topic") String topic) throws Exception {
        LOGGER.info("打开了Socket链接Open a html. userId={}, name={}", userId, topic);
        this.session = session;
        //webSocketSet中存当前对象
        webSocketSet.add(this);
        //在线人数加一
        addOnlineCount();
        listener = new RedisMessageListener();
        //放入session
        listener.setSession(session);
        //放入用户ID
        listener.setUserId(userId);
        //放入在线人数
        listener.setOnlineCount(getOnlineCount());
        container.addMessageListener(listener, new PatternTopic(topic));
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        getOnlineCount();
        container.removeMessageListener(listener);
        LOGGER.info("关闭了Socket链接Close a html. ");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        getOnlineCount();
        LOGGER.info("收到一条数据消息，Receive a message from client: " + message + session.getId());
        try {
            this.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.error("socket链接错误错误Error while html. ", error);
    }

    public void sendMessage(String message) throws Exception {
        if (this.session.isOpen()) {
            getOnlineCount();
            this.session.getBasicRemote().sendText("Send a message from server. " + message);
        }
    }

    public static synchronized int getOnlineCount() {
        System.out.println(new Date() + "在线人数为" + onlineCount);
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebsocketEndpoint.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebsocketEndpoint.onlineCount--;
    }
}