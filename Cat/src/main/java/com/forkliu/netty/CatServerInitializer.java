package com.forkliu.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


public class CatServerInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //http协议的编解码使用的,是HttpRequestDecoder和HttpResponseEncoder处理器组合
        //HttpRequestDecoder http请求的解码
        //HttpResponseEncoder http请求的编码
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("testHttpServerHandler",new CatHttpServerHandler());
    }
}
