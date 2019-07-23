package com.xxg.mnt.lesson13;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import javax.net.ssl.SSLContext;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

/**
 * @program: mnt
 * @description:
 * @author: liangzr
 * @create: 2019-07-23 14:32
 * 使用mina的客户端
 */
public class MinaClient {
    public static void main(String[] args) throws Exception {
        SSLContext sslContext = Client.getSSLContext();
        NioSocketConnector nioConnector = new NioSocketConnector();
        nioConnector.setDefaultRemoteAddress(new InetSocketAddress("127.0.0.1", 8084));
        nioConnector.getSessionConfig().setKeepAlive(true);
        DefaultIoFilterChainBuilder chain = nioConnector.getFilterChain();
        chain.addLast("ssl", new SslFilter(sslContext));  // SslFilter需要放在最前面
        chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                LineDelimiter.WINDOWS.getValue(),
                LineDelimiter. WINDOWS.getValue()  )));
        nioConnector.getFilterChain().addLast("_App_threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
        nioConnector.setHandler(new ClientHandle());
        ConnectFuture future = nioConnector.connect();
        future.awaitUninterruptibly();
        IoSession ioSession = future.getSession();
        String lines = "床前明月光试试";
        byte[] outputBytes = lines.getBytes("UTF-8");
        ioSession.write(lines.getBytes());
    }
}

class ClientHandle extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String line = (String) message;
        System.out.println("messageReceived:" + line);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }
}
