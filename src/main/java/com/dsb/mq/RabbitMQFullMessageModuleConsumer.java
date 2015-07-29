package com.dsb.mq;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.QueueingConsumer.Delivery;

/**
 * 全消息模型的消费者
 * @author daxinxin
 */
@Component("rabbitMQFullMessageModuleConsumer")
public class RabbitMQFullMessageModuleConsumer {

private static Connection conn = null;
    
    private static Channel channel = null;
    
    public void receieveMessage() {
        //1 获得连接
        conn = RabbitMQConnection.getConnection(RabbitMQConstant.IP_SERVER);
        try {
            //2 建立通道
            channel = conn.createChannel();
            /**
             * 3 声明交换机
             * 第一个参数指定交换机名称，生产者和消费者要对应，在发布信息的时候，也要匹配这个交换机的名字
             * 第二个参数是指定交换机类型（direct：是精确匹配、topic：模糊匹配、fanout：广播）
             * */
            channel.exchangeDeclare(RabbitMQConstant.EXCHANGE_NAME, "direct");
            //4 获取队列名称，相当于获取该队列的身份
            String queueName = channel.queueDeclare().getQueue();
            /**
             * 5 把交换机和队列进行绑定，这样生产者把消息发到交换机中，消费者这边把队列绑定对应的交换机，然后再去消费队列
             * 第一个参数为队列名称，相当于队列
             * 第二个参数为交换机名称，相当于交换机
             * 第三个参数是RoutingKey，消费者路由只有和生产者的routingKey一致，才能接收到生产者发送的信息
             * */
            channel.queueBind(queueName, RabbitMQConstant.EXCHANGE_NAME, RabbitMQConstant.ROUTING_KEY);
            //输出提示
            System.out.println("准备接受数据: ");
            //消息缓存
            QueueingConsumer comsumer = new QueueingConsumer(channel);
            /**
             * 第二个参数是代表消息确认，默认值为false（代表着开启消息确认）
             * 消息确认是消费者发送确认信号给RabbitMQ服务器（注意不是传递给生产者）
             * 将确认信号传递给生产者是另一种Confirms机制
             * */
            channel.basicConsume(queueName, false, comsumer);
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
}