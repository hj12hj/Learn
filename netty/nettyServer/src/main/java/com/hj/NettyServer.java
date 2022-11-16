package com.hj;

import com.hj.entity.Message;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: hj
 * @date: 2022/11/15
 * @time: 9:31 AM
 */
public class NettyServer {
    public static void main(String[] args) {

        new ServerBootstrap()
                .group(new NioEventLoopGroup()) // 1
                .channel(NioServerSocketChannel.class) // 2
                .childHandler(new ChannelInitializer<NioSocketChannel>() { // 3
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new MessageDecoder()); // 5
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<Message>() { // 6
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, Message msg) {
                                System.out.println(msg.toString());
                                ctx.writeAndFlush(msg);

                            }
                        });
                    }
                })
                .bind(8080); // 4


    }
}
