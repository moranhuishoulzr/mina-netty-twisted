package com.xxg.mnt.lesson10;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;

/**
 * @program: mnt
 * @description:
 * @author: liangzr
 * @create: 2019-05-27 14:22
 * Acceptor thread������߳�����TCP�����������µ����ӣ��������ӷ��䵽I/O processor thread����I/O processor thread������IO������
 * ÿ��NioSocketAcceptor����һ��Acceptor thread���߳������������á�
 */
public class TcpServer {
    public static void main(String[] args) throws Exception {
        IoAcceptor acceptor = new NioSocketAcceptor(4); // ����I/O processor thread�߳�����
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
        acceptor.getFilterChain().addLast("executor", new ExecutorFilter()); // ��TcpServerHandle�е�ҵ���߼��õ�ExecutorFilter���̳߳���ִ��
        acceptor.setHandler(new TcpServerHandle());
        acceptor.bind(new InetSocketAddress(8080));
    }
}

class TcpServerHandle extends IoHandlerAdapter {
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        // ���������и���̬��SQLҪִ��3��
        Thread.sleep(3000);
    }
}
