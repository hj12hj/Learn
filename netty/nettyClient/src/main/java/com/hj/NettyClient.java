package com.hj;

import com.hj.entity.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

/**
 * @author: hj
 * @date: 2022/11/15
 * @time: 9:40 AM
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
                .group(new NioEventLoopGroup()) // 1
                .channel(NioSocketChannel.class) // 2
                .handler(new ChannelInitializer<Channel>() { // 3
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new MessageEncoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<Message>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, Message msg) {
                                System.out.println(msg.toString());
                            }
                        });
                    }
                })
                .connect("127.0.0.1", 8080) // 4
                .sync() // 5
                .channel();// 6

        for (int i = 0; i < 100; i++) {
            channel.writeAndFlush(new Message("hello !!"+i)); // 7

        }
    }
}
