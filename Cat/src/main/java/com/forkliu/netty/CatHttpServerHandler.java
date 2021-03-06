package com.forkliu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.util.concurrent.TimeUnit;

//浏览器的特性会发送一个/favicon.ico请求，获取网站的图标
public class CatHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{

    //channelRead0是读取客户端的请求并且向客户端返回响应的一个方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //io.netty.handler.codec.http.DefaultHttpRequest,io.netty.handler.codec.http.LastHttpContent$1
        System.out.println(msg.getClass());

        //打印出远程的地址，/0:0:0:0:0:0:0:1: 49734，本地线程的49734端口的线程和netty进行通信
        System.out.println(ctx.channel().remoteAddress());
        TimeUnit.SECONDS.sleep(8);

        if(msg instanceof HttpRequest){
            HttpRequest httpRequest = (HttpRequest)msg;
            System.out.println("请求方法名："+httpRequest.method().name()); //get方法

            URI uri = new URI(httpRequest.uri());
            //使用浏览器访问localhost:8899会发送二次请求，其中有一次是localhost:8899/favicon.ico,这个url请求访问网站的图标
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求favicon.ico");
                return;
            }

            //向客户端返回的内容
            ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            ctx.writeAndFlush(response);

            //其实更合理的close连接应该判断是http1.O还是1.1来进行判断请求超时时间来断开channel连接。
            ctx.channel().close();
        }
    }


    //管道活跃
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        super.channelActive(ctx);
    }

    //管道注册
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registered");
        super.channelRegistered(ctx);
    }

    //通道被添加
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added");
        super.handlerAdded(ctx);
    }

    //管道不活跃了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive");
        super.channelInactive(ctx);
    }

    //取消注册
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel unregistered");
        super.channelUnregistered(ctx);
    }
}
