package com.xxg.mnt.lesson14.server;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class TempTLSServer {

	public static void main(String[] args) throws Exception {
		/** ������������������ **/
		SocketAcceptor acceptor = new NioSocketAcceptor();
		acceptor.setReuseAddress(true);
		/** ��ȡĬ�Ϲ����� **/
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		/** ���ñ���������Ͱ��ж�ȡ����ģʽ **/
		chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		/** �����¼������� **/
		acceptor.setHandler(new TempTLSServerHandler());
		/** ����󶨵��˶˿ں� **/
		acceptor.bind(new InetSocketAddress(50003));
		System.out.println("�������� [" + 50003 + "] �ȴ�����...");
	}
}
