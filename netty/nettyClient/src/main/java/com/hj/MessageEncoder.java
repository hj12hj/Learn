package com.hj;

import com.hj.entity.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author: hj
 * @date: 2022/11/15
 * @time: 10:12 AM
 */
public class MessageEncoder extends MessageToMessageEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeInt(msg.getHeader());
        byte[] bytes = msg.getBody().getBytes();
        byteBuf.writeBytes(bytes);
        out.add(byteBuf);
    }
}

