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
		/** 创建服务器端连接器 **/
		SocketAcceptor acceptor = new NioSocketAcceptor();
		acceptor.setReuseAddress(true);
		/** 获取默认过滤器 **/
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
//		SslFilter sslFilter = new SslFilter(BogusSslContextFactory.getInstance(true));
		SslFilter sslFilter = new SslFilter(SSL.getSSLContext_server());
		/** 设置加密过滤器 **/
		chain.addLast("sslFilter", sslFilter);
		/** 设置编码过滤器和按行读取数据模式 **/
		chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		/** 设置事件处理器 **/
		acceptor.setHandler(new TLSServerHandler());
		/** 服务绑定到此端口号 **/
		acceptor.bind(new InetSocketAddress(50003));
		System.out.println("服务器在 [" + 50003 + "] 等待连接...");
	}
}