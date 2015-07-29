package com.dsb.mq;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * 消息消费者（接受者）
 * @author daxinxin
 *
 */
@Component("rabbitMQConsumer")
public class RabbitMQConsumer {

    private static Connection conn = null;
    
    private static Channel channel = null;
    
    public void receieveMessage() {
        //1 获得连接
        conn = RabbitMQConnection.getConnection(RabbitMQConstant.IP_SERVER);
        try {
            //2 建立通道
            channel = conn.createChannel();
            /**
             * 3 声明队列
             * 第二个参数为true是声明队列为持久化队列，这样保证rabbitMQ挂掉的情况下能够记住我们的队列
             * 然后配合持久化消息，就能做到不丢失消息
             * */
            channel.queueDeclare(RabbitMQConstant.QUEUE_NAME, true, false, false, null);
            //输出提示
            System.out.println("Ready to Recieve message: ");
            //消息缓存
            QueueingConsumer comsumer = new QueueingConsumer(channel);
            //第二个参数是代表消息确认，默认值为false（代表着开启消息确认）
            channel.basicConsume(RabbitMQConstant.QUEUE_NAME, false, comsumer);
            while(true) {
                //在收到发送过来的消息之前，会一直进行阻塞
                try {
                    Delivery delivery = comsumer.nextDelivery();
                    System.out.println(new String(delivery.getBody(), "GBK"));
                } catch (ShutdownSignalException | ConsumerCancelledException
                        | InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private RabbitMQConsumer(){}
    
    private static class holder {
        private static final RabbitMQConsumer rabbitMQConsumer = new RabbitMQConsumer();
    }
    
    public static RabbitMQConsumer getHandler(){
        return holder.rabbitMQConsumer;
    }
    
}