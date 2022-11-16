package com.hj;

import com.hj.entity.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author: hj
 * @date: 2022/11/15
 * @time: 10:46 AM
 */
public class MessageDecoder extends ByteToMessageDecoder {

    int length = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() >= 4) {
            if (length == 0) {
                length = in.readInt();
            }
            if (in.readableBytes() >= length) {
                byte[] bytes = new byte[length];
                in.readBytes(bytes);
                String body = new String(bytes);
                Message message = new Message(body);
                out.add(message);
                length = 0;
            }
        }
    }
}
