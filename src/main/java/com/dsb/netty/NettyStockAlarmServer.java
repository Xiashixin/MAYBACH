package com.dsb.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 *  告警服务端
 * @author daxinxin
 */
public class NettyStockAlarmServer {

    /**
     * 启动服务端的通信通道
     * @throws Exception
     */
    public static void bind() throws Exception {
        //配置线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
              .channel(NioServerSocketChannel.class)
              .option(ChannelOption.SO_BACKLOG, 100)
              .handler(new LoggingHandler(LogLevel.INFO))
              .childHandler(new ChannelInitializer<SocketChannel>(){
                  @Override
                  public void initChannel(SocketChannel ch) throws Exception {
                      ch.pipeline().addLast(new ObjectDecoder(
                          1024 * 1024,
                          ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())
                      ));
                      ch.pipeline().addLast(new ObjectEncoder());
                      //这里把handler做成了单例，因为只负责一个业务场景数据
                      ch.pipeline().addLast(NettyStockAlarmServerHandler.getHandler());
                  }
              });
            //绑定端口，等待同步成功
            ChannelFuture f = b.bind(8000).sync();
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            //释放
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    
    private static class holder {
        private static final NettyStockAlarmServer nettyStockAlarmServer = new NettyStockAlarmServer();
    }
    
    private NettyStockAlarmServer(){}
    
    /**
     * 获取NettyStockAlarmServer的单例实例
     * @return
     */
    public static NettyStockAlarmServer getHandler(){
        return holder.nettyStockAlarmServer;
    }
}