package com.dsb.netty;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.springframework.stereotype.Component;

import com.dsb.websocket.StockAlarmHandler;

@Sharable
@Component
public class NettyStockAlarmServerHandler extends ChannelHandlerAdapter {
    
    private StockAlarmHandler stockAlarmHandler = new StockAlarmHandler();
    
    //接收客户端发送过来的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到【aw系统】发送过来的【库存告警】消息："+msg);
        this.sendTo(msg);
        
    }
    
    public void sendTo(Object msg){
        System.out.println("准备发送到前端的消息："+msg);
        stockAlarmHandler.sendStockAlarm(msg.toString());
    }
    
    //异常捕捉
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
    
    /**
     * 通过静态内部类的方式实现单例
     * */
    private static class holder {
        private static final NettyStockAlarmServerHandler nettyStockAlarmServerHandler = new NettyStockAlarmServerHandler();
    }
    
    private NettyStockAlarmServerHandler(){ }
    
    public static NettyStockAlarmServerHandler getHandler(){
        return holder.nettyStockAlarmServerHandler;
    }
}