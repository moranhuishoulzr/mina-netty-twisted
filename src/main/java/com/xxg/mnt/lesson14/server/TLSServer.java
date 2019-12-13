package com.xxg.mnt.lesson14.server;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.xxg.mnt.lesson14.SSL;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;


public class TLSServer {
	public static void main(String[] args) throws Exception {
		/** ������������������ **/
		SocketAcceptor acceptor = new NioSocketAcceptor();
		acceptor.setReuseAddress(true);
		/** ��ȡĬ�Ϲ����� **/
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
//		SslFilter sslFilter = new SslFilter(BogusSslContextFactory.getInstance(true));
		SslFilter sslFilter = new SslFilter(SSL.getSSLContext_server());
		/** ���ü��ܹ����� **/
		chain.addLast("sslFilter", sslFilter);
		/** ���ñ���������Ͱ��ж�ȡ����ģʽ **/
		chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		/** �����¼������� **/
		acceptor.setHandler(new TLSServerHandler());
		/** ����󶨵��˶˿ں� **/
		acceptor.bind(new InetSocketAddress(50003));
		System.out.println("�������� [" + 50003 + "] �ȴ�����...");
	}
}