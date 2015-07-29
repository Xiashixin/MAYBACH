package com.dsb.mq;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * 消息生产者（发送者、发起者）
 * @author daxinxin
 *
 */
@Service("rabbitMQProducer")
public class RabbitMQProducer {

    private static Connection conn = null;
    private static Channel channel = null;
    private boolean connectFlag = false;
    
    
    /**
     * 连接rabbit服务器并初始化消息通道
     */
    public void connect(String msg) {
        //1 获得连接
        conn = RabbitMQConnection.getConnection(RabbitMQConstant.IP_SERVER);
        try {
            //2 建立通道
            channel = conn.createChannel();
            //TODO 为什么发布的routingKey信息是队列的名称呢
            /**
             * 3 声明队列
             * 第二个参数为true是声明队列为持久化队列，这样保证rabbitMQ挂掉的情况下能够记住我们的队列
             * 然后配合持久化消息，就能做到不丢失消息
             * */
            channel.queueDeclare(RabbitMQConstant.QUEUE_NAME, true, false, false, null);
            this.setConnectFlag(true);
            this.sendMsg(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            //RabbitMQConnection.closeConnection(conn);
        }
    }
    
    public void sendMsg(String msg) {
        try {
            //输出提示
            System.out.println("准备发送 : " + msg);
            /**
             * 4 发布信息
             * 第二个参数就是队列的名字
             * 第三个参数标记消息持久化，MessageProperties.PERSISTENT_TEXT_PLAIN
             * 第四个参数因为内容中有中文，所以需要做字符集设置，设置为GBK
             * */
            channel.basicPublish("", RabbitMQConstant.QUEUE_NAME, 
                                     MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes("GBK"));
            //输出提示
            System.out.println("已发送 : " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    
    public boolean getConnectFlag() {
        return connectFlag;
    }

    public void setConnectFlag(boolean connectFlag) {
        this.connectFlag = connectFlag;
    }

    
}