package com.flydean23.socketserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author: hj
 * @date: 2022/11/16
 * @time: 12:46 PM
 */
public class WebSocketFrameHandle extends SimpleChannelInboundHandler<WebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame) throws Exception {
        System.out.println("收到消息"+webSocketFrame.retain().toString());
        channelHandlerContext.writeAndFlush(webSocketFrame.retain());
    }
}
