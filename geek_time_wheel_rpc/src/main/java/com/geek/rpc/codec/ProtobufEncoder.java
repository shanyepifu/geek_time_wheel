package com.geek.rpc.codec;

import com.geek.rpc.message.ProtoBufMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * PB 编码器
 */
public class ProtobufEncoder extends MessageToByteEncoder<ProtoBufMessage.MessageRequest> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtoBufMessage.MessageRequest messageRequest, ByteBuf byteBuf) throws Exception {
        // 将对象转换为byte
        byte[] bytes = messageRequest.toByteArray();
        int length = bytes.length;
        ByteBuf buf = Unpooled.buffer(ProtoBufConstants.FIXEDLENGTH + length);
        // 写入消息头： 消息长度
        buf.writeShort(length);// 先将消息长度写入，也就是消息头
        buf.writeBytes(bytes);// 消息体中包含我们要发送的数据
        byteBuf.writeBytes(buf);
    }
}
