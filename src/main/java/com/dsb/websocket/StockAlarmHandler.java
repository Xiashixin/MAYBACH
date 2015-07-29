package com.dsb.websocket;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.dsb.mq.RabbitMQFullMessageModuleConsumer;
import com.google.common.collect.Lists;


/**
 * @author daxinxin
 * 初始化一个websocket会话
 */
public class StockAlarmHandler extends TextWebSocketHandler {

    //存储所有和本服务建立链接的session
    private static final List<WebSocketSession> sessions = Lists.newArrayList();
    
    @Autowired
    private RabbitMQFullMessageModuleConsumer rabbitMQFullMessageModuleConsumer;
    
    /**
     * 建立session连接
     * */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //注册session
        sessions.add(session);
        System.out.println("有新成员加入......");
        rabbitMQFullMessageModuleConsumer.receieveMessage();
//        RabbitMQConsumer.getHandler().receieveMessage();
        //和业务系统建立通道，判断之前是否已经建立
//        try {
//            NettyStockAlarmServer.getHandler();
//            NettyStockAlarmServer.bind();
//        } catch(BindException e) {
//            System.out.println("这个应用端session已经绑定过了......");
//        } finally {
//            System.out.println("开启业务系统通信通道......");
//        }
    }
    
    /**
     * 连接错误
     * */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        //关闭session
        if(session.isOpen())
            session.close();
        //删除注册session
        sessions.remove(session);
    }

    /**
     * 关闭sessionl连接
     * */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //删除注册session
        sessions.remove(session);
        System.out.println("有成员关闭了连接。。。");
    }

    /**
     * 发送信息
     * */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("有失效的session。。。");
            //删除失效的session
            sessions.remove(session);
        }
    }
    
    /**
     * 发送库存预警
     * @param msg
     */
    public void sendStockAlarm(String msg){
        System.out.println(sessions.size());
        //类似于广播式发送，发送到所有在线成员当中（有效的session），然后在jsp中判断谁进行展现
        for(WebSocketSession session : sessions)
            try {
                this.handleTextMessage(session, new TextMessage(msg));
            } catch (Exception e) {
                e.printStackTrace();
                //如果session已失效，就要在注册的session集合中删除
                sessions.remove(session);
            }
    }
}