package com.dsb.mq;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * 全消息模型的生产者
 * @author daxinxin
 */
@Component("rabbitMQFullMessageModuleProducer")
public class RabbitMQFullMessageModuleProducer {

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
             * 3 声明交换机
             * 第一个参数指定交换机名称，生产者和消费者要对应，在发布信息的时候，也要匹配这个交换机的名字
             * 第二个参数是指定交换机类型（direct：是精确匹配、topic：模糊匹配、fanout：广播）
             * */
            channel.exchangeDeclare(RabbitMQConstant.EXCHANGE_NAME, "direct");
            channel.confirmSelect();
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
             * 第一个参数指定交换机的名字，将信息发布到交换机上
             * 第二个参数就是队列的名字，所以这里不用队列了
             * 第三个参数标记消息持久化，MessageProperties.PERSISTENT_TEXT_PLAIN
             * 第四个参数因为内容中有中文，所以需要做字符集设置，设置为GBK
             * */
            channel.basicPublish(RabbitMQConstant.EXCHANGE_NAME, RabbitMQConstant.ROUTING_KEY, 
                                     MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes("GBK"));
            System.out.println("已发送完成 : " + msg);
            channel.waitForConfirmsOrDie();
            System.out.println("消费者已确认 : " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("消费者未确认！");
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