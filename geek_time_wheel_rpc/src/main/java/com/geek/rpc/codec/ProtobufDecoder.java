package com.geek.rpc.codec;

import com.geek.rpc.message.ProtoBufMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


public class ProtobufDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 标记一下当前的readIndex的位置
        byteBuf.markReaderIndex();
        // 判断包头长度
        if (byteBuf.readableBytes() < 2) {// 不够包 头
            return;
        }
        // 读取传送过来的消息的长度。
        int length = byteBuf.readUnsignedShort();
        // 长度如果小于0
        if (length < 0) {// 非法数据，关闭连接
            channelHandlerContext.close();
        }
        // 读到的消息体长度如果小于传送过来的消息长度
        if (length > byteBuf.readableBytes()) {
            // 重置读取位置
            byteBuf.resetReaderIndex();
            return;
        }
        ByteBuf body = Unpooled.buffer(length);
        byteBuf.readBytes(body);
        try {
            // 字节转成对象
            ProtoBufMessage.MessageRequest msg = ProtoBufMessage.MessageRequest.parseFrom(body.array());
            if (msg != null) {
                // 获取业务消息头
                list.add(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
