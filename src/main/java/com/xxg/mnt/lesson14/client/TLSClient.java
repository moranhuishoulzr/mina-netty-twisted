package com.xxg.mnt.lesson14.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

import com.xxg.mnt.lesson14.SSL;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;


public class TLSClient {
    public static void main(String[] args) throws GeneralSecurityException, Exception {
        /** �����ͻ��������� **/
        IoConnector connector = new NioSocketConnector();
        //SslFilter connectorTLSFilter = new SslFilter(BogusSslContextFactory.getInstance(false));
        SslFilter connectorTLSFilter = new SslFilter(SSL.getSSLContext_client());
        /** ����Ϊ�ͻ���ģʽ **/
        connectorTLSFilter.setUseClientMode(true);
        /** ���ü��ܹ����� **/
        connector.getFilterChain().addLast("SSL", connectorTLSFilter);
        /** �����¼������� **/
        connector.setHandler(new TLSClientHandler());
        /** ���ñ���������Ͱ��ж�ȡ����ģʽ **/
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        /** �������� **/
        ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 50003));
        /** �ȴ����Ӵ������ **/
        future.awaitUninterruptibly();
        /** ��ȡ���ӻỰ **/
        IoSession session = future.getSession();
        /** ������Ϣ **/
        session.write("���ǰ�ȫ����?");
        /** �ȴ����ӶϿ� **/
        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();
    }
}
