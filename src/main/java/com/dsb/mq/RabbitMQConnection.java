package com.dsb.mq;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConnection {

    public static Connection conn = null;
    
    /**
     * get connection
     * @param ip_add
     * @return
     */
    public static Connection getConnection(String ip_add) {
        ConnectionFactory factory = new ConnectionFactory();
        //指定RabbitMQ主机地址
        factory.setHost(ip_add);
        factory.setPort(5672);
        factory.setUsername("daxinxin");
        factory.setPassword("daxinxin");
        try {
            conn = factory.newConnection();
            return conn;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * close connection
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
