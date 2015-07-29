package com.dsb.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author daxinxin
 * 把StockAlarmHandler声明为一个实例，不使用servlet容器扫描
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * 注册为spring管理的bean
     * */
    @Bean
    public WebSocketHandler stockAlarmHandler(){
        return new StockAlarmHandler();
    }

    /**
     * 注册服务
     * */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //注册alarmHandler服务
        registry.addHandler(stockAlarmHandler(), "/stockAlarmHandler");
    }
}