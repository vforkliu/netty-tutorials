package com.forkliu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class CatServer {
    public static void main(String[] args) throws Exception{
        //bossGroup是获取连接的，workerGroup是用来处理连接的，这二个线程组都是死循环
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            //简化服务端启动的一个类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //group有二个重载方法，一个是接收一个EventLoopGroup类型参数的方法，一个是接收二个EventLoopGroup类型的参数的方法
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new CatServerInitializer());

            ChannelFuture  channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
