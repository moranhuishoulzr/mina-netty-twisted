package com.xxg.mnt.lesson04;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by wucao on 17/2/27.
 */
public class MyNettyDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // ���û�н�����Header���֣�4�ֽڣ���ֱ���˳��÷���
        if(in.readableBytes() >= 4) {

            // ��ǿ�ʼλ�ã����һ����Ϣû��������򷵻ص����λ��
            in.markReaderIndex();

            byte[] bytes = new byte[4];
            in.readBytes(bytes); // ��ȡ4�ֽڵ�Header

            int bodyLength = LittleEndian.getLittleEndianInt(bytes); // header��С�ֽ���תint

            // ���bodyû�н�������
            if(in.readableBytes() < bodyLength) {
                in.resetReaderIndex(); // ByteBuf�ص����λ��
            } else {
                byte[] bodyBytes = new byte[bodyLength];
                in.readBytes(bodyBytes);
                String body = new String(bodyBytes, "UTF-8");
                out.add(body); // ������һ����Ϣ
            }
        }
    }
}